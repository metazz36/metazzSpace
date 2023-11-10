package com.metazz.metazzspace.common.exception;

import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.response.ER;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ER handleBaseException(BaseException e,HttpServletRequest request) {
        log.error(e.getMessage(),e);
        return new ER(e.getExceptionEnum().getCode(),e.getExceptionEnum().getMessage());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ER handleValidException(MethodArgumentNotValidException e,HttpServletRequest request) {
        log.error("参数绑定校验异常",e);
        return wrapperBindingResult(e.getBindingResult(),request);
    }

    /**
     * 未定义异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ER handleException(Exception e,HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (ENV_PROD.equals(profile)) {
            // 为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息
            return new ER(ExceptionEnum.SERVER_ERROR.getCode(),  ExceptionEnum.SERVER_ERROR.getMessage());
        }
        return new ER(ExceptionEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

    private ER wrapperBindingResult(BindingResult bindingResult, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        for(ObjectError error:bindingResult.getAllErrors()) {
            sb.append(", ");
            if(error instanceof FieldError) {
                sb.append(((FieldError) error).getField()).append(": ");
                sb.append(error.getDefaultMessage() == null ? "":error.getDefaultMessage());
            }
        }
        return new ER(ExceptionEnum.VALID_ERROR.getCode(),ExceptionEnum.VALID_ERROR.getMessage() + " : " + sb.substring(2));
    }

}
