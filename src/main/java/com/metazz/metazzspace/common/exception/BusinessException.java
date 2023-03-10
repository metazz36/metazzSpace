package com.metazz.metazzspace.common.exception;

import com.metazz.metazzspace.common.exception.utils.IResponseEnum;

public class BusinessException extends BaseException{

    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

}
