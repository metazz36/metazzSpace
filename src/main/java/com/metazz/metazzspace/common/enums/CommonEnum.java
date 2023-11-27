package com.metazz.metazzspace.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonEnum {

    /**
     * 用户状态
     */
    USER_STATUS_ENABLE("0","无效"),
    USER_STATUS_DISABLE("1","有效"),

    /**
     * 用户类型
     */
    USER_TYPE_USER("0", "用户"),
    USER_TYPE_ADMIN("1", "管理员"),

    ;

    private String code;

    private String message;
}
