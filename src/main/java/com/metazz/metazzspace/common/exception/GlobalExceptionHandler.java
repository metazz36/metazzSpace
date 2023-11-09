package com.metazz.metazzspace.common.exception;

import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.response.ER;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

}
