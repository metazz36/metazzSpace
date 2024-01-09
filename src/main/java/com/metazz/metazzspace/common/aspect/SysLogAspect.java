package com.metazz.metazzspace.common.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.util.UserUtil;
import com.metazz.metazzspace.model.entity.VisitLog;
import com.metazz.metazzspace.service.IVisitLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Aspect
@Component
@Slf4j
@Order(1)
public class SysLogAspect {

    private static final String UNKNOWN = "unknown";

    private VisitLog visitLog = null;

    @Autowired
    IVisitLogService visitLogService;

    @Around("@annotation(sysLog)")
    public Object aroundLog(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        Object result = null;
        // 填充请求参数
        setRequestParam(point,sysLog,attributes);
        try {
            // 执行方法
            result = point.proceed();
        } catch (Exception e) {
            if(StrUtil.isBlank(e.getMessage())){
                visitLog.setExceptionInfo("未知异常");
            }else{
                visitLog.setExceptionInfo(e.getMessage());
            }
            throw e;
        } finally {
            // 填充响应参数
            setResponseParam(result);
            // 保存系统日志
            visitLogService.save(visitLog);
        }
        return result;
    }

    /**
     * 填充请求参数
     *
     * @param point
     * @param sysLog
     * @param attributes
     */
    private void setRequestParam(ProceedingJoinPoint point,SysLog sysLog,ServletRequestAttributes attributes){
        HttpServletRequest request = attributes.getRequest();
        visitLog = new VisitLog();
        if(Optional.ofNullable(UserUtil.getUser()).isPresent()){
            visitLog.setUserId(UserUtil.getUser().getId());
        }
        visitLog.setIp(getIp(request));
        visitLog.setUrl(request.getRequestURI());
        visitLog.setHttpMethod(request.getMethod());
        visitLog.setClassMethod(sysLog.operateName());
        visitLog.setRequestTime(new Date());
        visitLog.setRequestParams(JSONUtil.toJsonStr(getNameAndValue(point)));
    }

    /**
     * 填充响应参数
     *
     * @param result
     */
    private void setResponseParam(Object result) {
        visitLog.setResponseParams(JSONUtil.toJsonStr(result));
        visitLog.setResponseTime(new Date());
    }

    /**
     * 获取参数
     *
     * @param joinPoint
     * @return 参数
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    /**
     * 获取IP地址
     *
     * @param request 请求参数
     * @return IP地址
     */
    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            try {
                // 获取本机真正的ip地址
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

}
