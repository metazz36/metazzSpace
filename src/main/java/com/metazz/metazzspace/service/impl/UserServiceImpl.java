package com.metazz.metazzspace.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.constant.CommonConstant;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.util.MailUtil;
import com.metazz.metazzspace.common.util.MetazzUtil;
import com.metazz.metazzspace.common.util.UserUtil;
import com.metazz.metazzspace.model.dto.ModifyPasswordDTO;
import com.metazz.metazzspace.model.dto.UserLoginDTO;
import com.metazz.metazzspace.model.dto.UserModifyDTO;
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
    public void getCode(String email,String purpose) {
        int i = new Random().nextInt(900000) + 100000;
        log.info(email + "的验证码为: {}",i);
        // 将验证码保存到Redis中
        if("0".equals(purpose)){
            redisTemplate.opsForValue().set(CommonConstant.USER_REGISTER_CODE + email,String.valueOf(i),CommonConstant.CODE_EXPIRE, TimeUnit.MINUTES);
        }else{
            redisTemplate.opsForValue().set(CommonConstant.PASSWORD_MODIFY_CODE + email,String.valueOf(i),CommonConstant.CODE_EXPIRE, TimeUnit.MINUTES);
        }
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
        String code = (String) redisTemplate.opsForValue().get(CommonConstant.USER_REGISTER_CODE + userRegisterDTO.getEmail());
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
        // 将密码隐藏，再放到缓存中
        user.setPassword(null);
        // 生成token,保存到Redis，并返回给前端（前端请求头携带此token）
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(CommonConstant.USER_TOKEN + uuid,user,CommonConstant.USER_TOKEN_EXPIRE,TimeUnit.DAYS);
        return CommonConstant.USER_TOKEN + uuid;
    }

    @Override
    public void exit() {
        // 将token从Redis移除
        String token = MetazzUtil.getToken();
        redisTemplate.delete(token);
    }

    @Override
    public void modifyUser(UserModifyDTO userModifyDTO) {
        if (userModifyDTO.getUserName().contains("@")) {
            // 用户名不能包含@
            throw new BaseException(ExceptionEnum.USER_NAME_ERROR);
        }
        Integer count = lambdaQuery().eq(User::getUserName, userModifyDTO.getUserName()).ne(User::getId, UserUtil.getUser().getId()).count();
        if (count != 0) {
            // 用户名已被注册
            throw new BaseException(ExceptionEnum.USER_NAME_HAS_BEEN_REGISTERED);
        }
        // MySQL更新用户信息
        User user = new User();
        user.setId(UserUtil.getUser().getId());
        user.setUserName(userModifyDTO.getUserName());
        user.setNickName(userModifyDTO.getUserName());
        user.setGender(userModifyDTO.getGender());
        user.setAvatar(userModifyDTO.getAvatar());
        user.setBirthday(userModifyDTO.getBirthday());
        user.setIntroduction(userModifyDTO.getIntroduction());
        updateById(user);
        // Redis更新用户信息
        User one = lambdaQuery().eq(User::getId, user.getId()).one();
        one.setPassword(null);
        redisTemplate.opsForValue().set(MetazzUtil.getToken(),one,CommonConstant.USER_TOKEN_EXPIRE,TimeUnit.DAYS);
    }

    @Override
    public void modifyPassword(ModifyPasswordDTO modifyPasswordDTO) {
        String code = (String) redisTemplate.opsForValue().get(CommonConstant.PASSWORD_MODIFY_CODE + modifyPasswordDTO.getEmail());
        if(StrUtil.isBlank(code) || !modifyPasswordDTO.getCode().equals(code)) {
            // 验证码错误
            throw new BaseException(ExceptionEnum.CODE_ERROR);
        }
        // Redis删除该Key，确保一个验证码只能进行一次密码修改
        redisTemplate.delete(CommonConstant.PASSWORD_MODIFY_CODE + modifyPasswordDTO.getEmail());
        User user = lambdaQuery().eq(User::getEmail, modifyPasswordDTO.getEmail()).one();
        if (!Optional.ofNullable(user).isPresent()) {
            // 邮箱没有关联的账号
            throw new BaseException(ExceptionEnum.MAIL_HAS_NO_ASSOCIATED_ACCOUNT);
        }
        if(CommonEnum.USER_STATUS_ENABLE.getCode().equals(user.getStatus())){
            // 用户状态无效
            throw new BaseException(ExceptionEnum.USER_STATUS_DISABLE);
        }
        // 更新密码
        User one = new User();
        one.setId(user.getId());
        String password = new String(SecureUtil.aes(CommonConstant.PASSWORD_KEY.getBytes(StandardCharsets.UTF_8)).encrypt(modifyPasswordDTO.getPassword()));;
        one.setPassword(password);
        updateById(one);
    }

}
