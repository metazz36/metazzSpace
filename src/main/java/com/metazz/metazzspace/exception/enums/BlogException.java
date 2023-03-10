package com.metazz.metazzspace.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogException implements BusinessExceptionAssert {

    /**
     * 博客已存在
     */
    BLOG_EXISTS(1001,"博客已存在"),
    ;

    private Integer code;
    private String message;
}
