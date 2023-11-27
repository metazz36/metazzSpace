package com.metazz.metazzspace.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginCheck {

    /**
     * 用户类型(0-普通用户  1-管理员)
     */
    String useType() default "0";

}
