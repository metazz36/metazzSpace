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

    ;

    private String code;

    private String message;
}
