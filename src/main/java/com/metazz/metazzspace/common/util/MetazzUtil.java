package com.metazz.metazzspace.common.util;

import com.metazz.metazzspace.common.constant.CommonConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

public class MetazzUtil {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getToken(){
        return MetazzUtil.getRequest().getHeader(CommonConstant.TOKEN_HEADER);
    }

}
