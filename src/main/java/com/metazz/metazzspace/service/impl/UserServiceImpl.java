package com.metazz.metazzspace.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.constant.CommonConstant;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.util.MailUtil;
import com.metazz.metazzspace.model.dto.UserLoginDTO;
import com.metazz.metazzspace.model.dto.UserRegisterDTO;
import com.metazz.metazzspace.model.entity.User;
import com.metazz.metazzspace.mapper.UserMapper;
import com.metazz.metazzspace.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    MailUtil mailUtil;

    @Override
    public void getCode(String email) {
        // 邮箱已被注册
        Integer emailCount = lambdaQuery().eq(User::getEmail, email).count();
        if(emailCount != 0){
            throw new BaseException(ExceptionEnum.EMAIL_HAS_BEEN_REGISTERED);
        }
        int i = new Random().nextInt(900000) + 100000;
        log.info(email + "的验证码为: {}",i);
        // 将验证码保存到Redis中
        redisTemplate.opsForValue().set(CommonConstant.USER_CODE + email,String.valueOf(i),CommonConstant.USER_CODE_EXPIRE, TimeUnit.MINUTES);
        // 通过邮件发送验证码
        List<String> to = new ArrayList<>();
        to.add(email);
        String text = mailUtil.getCodeMail(i);
        mailUtil.sendMailMessage(to, MailUtil.commonSubject, text);
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO.getUserName().contains("@")) {
            // 用户名不能包含@
            throw new BaseException(ExceptionEnum.USER_NAME_ERROR);
        }
        String code = (String) redisTemplate.opsForValue().get(CommonConstant.USER_CODE + userRegisterDTO.getEmail());
        Integer userNameCount = lambdaQuery().eq(User::getUserName, userRegisterDTO.getUserName()).count();
        if(userNameCount != 0){
            // 用户名已被注册
            throw new BaseException(ExceptionEnum.USER_NAME_HAS_BEEN_REGISTERED);
        }
        Integer emailCount = lambdaQuery().eq(User::getEmail, userRegisterDTO.getEmail()).count();
        if(emailCount != 0){
            // 邮箱已被注册
            throw new BaseException(ExceptionEnum.EMAIL_HAS_BEEN_REGISTERED);
        }
        if(StrUtil.isBlank(code) || !userRegisterDTO.getCode().equals(code)) {
            // 验证码错误
            throw new BaseException(ExceptionEnum.CODE_ERROR);
        }
        User user = new User();
        user.setUserName(userRegisterDTO.getUserName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(new String(SecureUtil.aes(CommonConstant.PASSWORD_KEY.getBytes(StandardCharsets.UTF_8)).encrypt(userRegisterDTO.getPassword())));
        user.setNickName(userRegisterDTO.getUserName());
        save(user);
    }

    @Override
    public String loginUser(UserLoginDTO userLoginDTO) {
        String password = new String(SecureUtil.aes(CommonConstant.PASSWORD_KEY.getBytes(StandardCharsets.UTF_8)).encrypt(userLoginDTO.getPassword()));;
        User user = lambdaQuery().and(wrapper -> wrapper
                .eq(User::getUserName, userLoginDTO.getAccount())
                .or()
                .eq(User::getEmail, userLoginDTO.getAccount()))
                .eq(User::getPassword, password)
                .one();
        if (!Optional.ofNullable(user).isPresent()) {
            // 账号或密码错误
            throw new BaseException(ExceptionEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if(CommonEnum.USER_STATUS_ENABLE.getCode().equals(user.getStatus())){
            // 用户状态无效
            throw new BaseException(ExceptionEnum.USER_STATUS_DISABLE);
        }
        // 生成token,保存到Redis，并返回给前端（前端请求头携带此token）
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(CommonConstant.USER_TOKEN + token,user,CommonConstant.USER_TOKEN_EXPIRE,TimeUnit.DAYS);
        return token;
    }

}
