package com.metazz.metazzspace.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum{

    /**
     * 服务器异常
     */
    SERVER_ERROR("0000","服务器异常"),

    /**
     * 博客已存在
     */
    BLOG_EXISTS("0001","博客已存在"),

    /**
     * 分类名字已存在
     */
    CATEGORY_NAME_EXISTS("0002","分类名字已存在"),


    /**
     * 分类不存在
     */
    CATEGORY_NOT_EXISTS("0003","分类不存在"),

    /**
     * 标签名字已存在
     */
    LABEL_NAME_EXISTS("0004","标签名字已存在"),


    /**
     * 标签不存在
     */
    LABEL_NOT_EXISTS("0005","标签不存在"),

    ;

    private String code;

    private String message;

}
