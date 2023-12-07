package com.metazz.metazzspace.common.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.util.MetazzUtil;
import com.metazz.metazzspace.common.util.UserUtil;
import com.metazz.metazzspace.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class LoginCheckAspect {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Around("@annotation(loginCheck)")
    public Object aroundLog(ProceedingJoinPoint point, LoginCheck loginCheck) throws Throwable {
        String token = MetazzUtil.getToken();
        if(StrUtil.isBlank(token)){
            // 未登陆
            throw new BaseException(ExceptionEnum.NOT_LOGIN);
        }
        Object o = redisTemplate.opsForValue().get(token);
        User user = BeanUtil.toBean(o, User.class);
        if(!Optional.ofNullable(user).isPresent()){
            // 登录已过期
            throw new BaseException(ExceptionEnum.LOGIN_EXPIRED);
        }
        if (CommonEnum.USER_TYPE_USER.getCode().equals(user.getUserTag()) && loginCheck.useType().equals(CommonEnum.USER_TYPE_ADMIN.getCode())) {
            // 权限不足
            throw new BaseException(ExceptionEnum.PERMISSION_DENIED);
        }
        // 用户信息保存到ThreadLocal中
        UserUtil.setUser(user);
        // 执行目标方法
        Object proceed = point.proceed();
        // 从ThreadLocal移除用户信息
        UserUtil.removeUser();
        return proceed;
    }

}
