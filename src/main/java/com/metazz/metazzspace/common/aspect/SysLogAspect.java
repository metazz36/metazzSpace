package com.metazz.metazzspace.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
@Slf4j
public class SysLogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.metazz.metazzspace.common.annotation.SysLog)")
    public void sysLog() {
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("sysLog()")
    public void aroundLog(ProceedingJoinPoint point) throws Throwable {
        System.out.println("====日志切面====");
    }

}
