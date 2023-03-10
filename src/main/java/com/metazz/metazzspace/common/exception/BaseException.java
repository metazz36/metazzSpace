package com.metazz.metazzspace.common.exception;

import com.metazz.metazzspace.common.exception.utils.IResponseEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private IResponseEnum responseEnum;

    private Integer errorCode;

    private String errorMsg;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.errorCode = responseEnum.getCode();
        this.errorMsg = responseEnum.getMessage();
        this.responseEnum = responseEnum;
    }

}
