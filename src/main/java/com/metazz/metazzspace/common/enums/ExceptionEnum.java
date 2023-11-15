package com.metazz.metazzspace.common.enums;

import com.metazz.metazzspace.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum{

    SERVER_ERROR(CommonConstant.SERVER_ERROR,"服务器异常"),

    VALID_ERROR(CommonConstant.VALID_ERROR,"参数校验异常"),

    BLOG_EXISTS(CommonConstant.BUSINESS_ERROR,"博客已存在"),

    BLOG_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"博客不存在"),

    BLOG_TITLE_EXISTS(CommonConstant.BUSINESS_ERROR,"博客标题存在"),

    CATEGORY_NAME_EXISTS(CommonConstant.BUSINESS_ERROR,"分类名字已存在"),

    CATEGORY_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"分类不存在"),

    LABEL_NAME_EXISTS(CommonConstant.BUSINESS_ERROR,"标签名字已存在"),

    LABEL_NOT_EXISTS(CommonConstant.BUSINESS_ERROR,"标签不存在"),

    CATEGORY_LINKED_BLOG(CommonConstant.BUSINESS_ERROR,"该分类关联了博客，无法删除"),

    LABEL_LINKED_BLOG(CommonConstant.BUSINESS_ERROR,"该标签关联了博客，无法删除");

    ;

    private String code;

    private String message;

}
