package com.metazz.metazzspace.common.exception.handler;

import cn.hutool.core.util.StrUtil;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.exception.BusinessException;
import com.metazz.metazzspace.common.exception.enums.ArgumentResponseEnum;
import com.metazz.metazzspace.common.exception.enums.CommonResponseEnum;
import com.metazz.metazzspace.common.response.ER;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ResourceBundle;

/**
 * 全局异常处理
 */
@Slf4j
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String ENV_PROD = "prod";

    @Value("${spring.profiles.active}")
    private String profile;

    public String getMessage(BaseException e,HttpServletRequest request) {
        String code = e.getResponseEnum().toString();
        //request.getLocale()获取请求头的Accept-Language，如果Accept-Language为空，则取服务器本地的locale
        ResourceBundle bundle = ResourceBundle.getBundle("messages",request.getLocale());
        String message = bundle.getString(code);
        if(StrUtil.isBlankIfStr(message)) {
            message = e.getErrorMsg();
        }
        return message;
    }

    /**
     * 业务异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ER handleBusinessException(BaseException e,HttpServletRequest request) {
        log.error(e.getMessage(),e);
        return new ER(e.getResponseEnum().getCode(),getMessage(e, request));
    }

    /**
     * 自定义异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ER handleBaseException(BaseException e,HttpServletRequest request) {
        log.error(e.getMessage(),e);
        return new ER(e.getResponseEnum().getCode(),getMessage(e, request));
    }

    /**
     * 参数绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ER handleBindException(BindException e,HttpServletRequest request) {
        log.error("参数绑定校验异常",e);
        return wrapperBindingResult(e.getBindingResult(),request);
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ER handleValidException(MethodArgumentNotValidException e,HttpServletRequest request) {
        log.error("参数绑定校验异常",e);
        return wrapperBindingResult(e.getBindingResult(),request);
    }

    private ER wrapperBindingResult(BindingResult bindingResult, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ResourceBundle bundle = ResourceBundle.getBundle("message",request.getLocale());
        for(ObjectError error:bindingResult.getAllErrors()) {
            sb.append(", ");
            if(error instanceof FieldError) {
                sb.append(((FieldError) error).getField()).append(": ");
            }
            sb.append(error.getDefaultMessage() == null ? "":bundle.getString(error.getDefaultMessage()));
        }

        return new ER(ArgumentResponseEnum.VALID_ERROR.getCode(),sb.substring(2));
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ER constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", request.getLocale());
        StringBuilder code = new StringBuilder();
        e.getConstraintViolations().forEach(
                m -> {
                    code.append(m.getMessage() == null ? "" : bundle.getString(m.getMessage()));
                }
        );
        return new ER(HttpStatus.BAD_REQUEST.value(), ArgumentResponseEnum.VALID_ERROR.getCode(), code.toString());
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ER handleException(Exception e,HttpServletRequest request) {
        log.error(e.getMessage(), e);

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            int code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(baseException,request);
            return new ER(code, message);
        }

        return new ER(CommonResponseEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

}
