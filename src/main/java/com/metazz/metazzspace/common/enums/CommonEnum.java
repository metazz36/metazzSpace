package com.metazz.metazzspace.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonEnum {

    /**
     * 状态
     */
    DISABLE("0","无效"),
    ENABLE("1","有效"),

    /**
     * 是否
     */
    NO("0","否"),
    YES("1","是"),

    /**
     * 用户类型
     */
    USER_TYPE_USER("0", "用户"),
    USER_TYPE_ADMIN("1", "管理员"),

    /**
     * 评论类型
     */
    COMMENT_TYPE_BLOG("0","博客"),
    COMMENT_TYPE_CHAT("1","说说"),
    COMMENT_TYPE_MESSAGE("2","全站留言"),

    ;

    private String code;

    private String message;
}
