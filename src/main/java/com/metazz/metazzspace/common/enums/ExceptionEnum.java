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

    LABEL_LINKED_BLOG(CommonConstant.BUSINESS_ERROR,"该标签关联了博客，无法删除"),

    USER_NAME_ERROR(CommonConstant.BUSINESS_ERROR,"用户名不能包含@"),

    CODE_ERROR(CommonConstant.BUSINESS_ERROR,"验证码错误"),

    EMAIL_HAS_BEEN_REGISTERED(CommonConstant.BUSINESS_ERROR,"邮箱已被注册"),

    USER_NAME_HAS_BEEN_REGISTERED(CommonConstant.BUSINESS_ERROR,"用户名已被注册"),

    ACCOUNT_OR_PASSWORD_ERROR(CommonConstant.BUSINESS_ERROR,"账号或密码错误"),

    USER_STATUS_DISABLE(CommonConstant.BUSINESS_ERROR,"用户状态无效"),

    NOT_LOGIN(CommonConstant.BUSINESS_ERROR, "未登陆，请登陆后再进行操作"),

    LOGIN_EXPIRED(CommonConstant.BUSINESS_ERROR, "登录已过期，请重新登陆"),

    PERMISSION_DENIED(CommonConstant.BUSINESS_ERROR, "权限不足"),


    ;

    private String code;

    private String message;

}
