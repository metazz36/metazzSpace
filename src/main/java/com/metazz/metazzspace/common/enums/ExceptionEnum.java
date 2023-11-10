package com.metazz.metazzspace.common.enums;

import com.metazz.metazzspace.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum{

    SERVER_ERROR(CommonConstant.SERVER_ERROR,"服务器异常"),

    BLOG_EXISTS(CommonConstant.BUSINESS_ERROR,"博客已存在"),

    BLOG_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"博客不存在"),

    CATEGORY_NAME_EXISTS(CommonConstant.BUSINESS_ERROR,"分类名字已存在"),

    CATEGORY_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"分类不存在"),

    LABEL_NAME_EXISTS(CommonConstant.BUSINESS_ERROR,"标签名字已存在"),

    LABEL_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"标签不存在"),

    ;

    private String code;

    private String message;

}
