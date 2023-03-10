package com.metazz.metazzspace.common.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BatchBusinessExceptionEnum implements BusinessExceptionAssert {

    BAD_LICENCE_TYPE(1001,"Bank Core Exception");

    private Integer code;
    private String message;

}
