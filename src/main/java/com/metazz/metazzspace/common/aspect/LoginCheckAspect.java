package com.metazz.metazzspace.common.aspect;

import cn.hutool.core.util.StrUtil;
import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.constant.CommonConstant;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class LoginCheckAspect {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Around("@annotation(loginCheck)")
    public void aroundLog(ProceedingJoinPoint point, LoginCheck loginCheck) throws Throwable {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(CommonConstant.TOKEN_HEADER);
        if(StrUtil.isBlank(token)){
            // 未登陆
            throw new BaseException(ExceptionEnum.NOT_LOGIN);
        }
        User user = (User) redisTemplate.opsForValue().get(token);
        if(!Optional.ofNullable(user).isPresent()){
            // 登录已过期
            throw new BaseException(ExceptionEnum.LOGIN_EXPIRED);
        }
        if (CommonEnum.USER_TYPE_USER.getCode().equals(user.getUserTag()) && loginCheck.useType().equals(CommonEnum.USER_TYPE_ADMIN.getCode())) {
            // 权限不足
            throw new BaseException(ExceptionEnum.PERMISSION_DENIED);
        }
        // 重置过期时间
        redisTemplate.opsForValue().set(token,user,CommonConstant.USER_TOKEN_EXPIRE, TimeUnit.DAYS);
    }

}
