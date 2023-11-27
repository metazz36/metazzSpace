package com.metazz.metazzspace.common.aspect;

import com.metazz.metazzspace.common.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    public void aroundLog(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {

    }

}
