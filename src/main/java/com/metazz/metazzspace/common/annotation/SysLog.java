package com.metazz.metazzspace.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 操作名称
     *
     * @return
     */
    String operateName() default "";

}

