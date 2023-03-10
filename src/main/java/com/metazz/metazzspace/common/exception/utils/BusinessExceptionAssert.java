package com.metazz.metazzspace.common.exception.utils;

import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.exception.BusinessException;

public interface BusinessExceptionAssert extends IResponseEnum,Assert{

    @Override
    default BaseException newException() {
        return new BusinessException(this);
    }
}
