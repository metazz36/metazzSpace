package com.metazz.metazzspace.common.exception;

import com.metazz.metazzspace.common.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    private String errorCode;

    private String errorMsg;

    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.errorCode = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getMessage();
        this.exceptionEnum = exceptionEnum;
    }

}
