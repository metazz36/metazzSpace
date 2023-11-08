package com.metazz.metazzspace.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MyException implements BusinessExceptionAssert {

    /**
     * 博客已存在
     */
    BLOG_EXISTS(1001,"博客已存在"),


    /**
     * 分类名字已存在
     */
    CATEGORY_NAME_EXISTS(1002,"分类名字已存在"),


    /**
     * 分类不存在
     */
    CATEGORY_NOT_EXISTS(1003,"分类不存在"),

    /**
     * 标签名字已存在
     */
    LABEL_NAME_EXISTS(1004,"标签名字已存在"),


    /**
     * 标签不存在
     */
    LABEL_NOT_EXISTS(1005,"标签不存在"),


    ;

    private Integer code;
    private String message;
}
