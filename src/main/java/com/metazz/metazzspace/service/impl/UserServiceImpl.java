package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.util.MailUtil;
import com.metazz.metazzspace.model.entity.User;
import com.metazz.metazzspace.mapper.UserMapper;
import com.metazz.metazzspace.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    MailUtil mailUtil;

    @Override
    public void getCode(String emailOrPhone) {
        int i = new Random().nextInt(900000) + 100000;
        log.info(emailOrPhone + "的验证码为: {}",i);
        // 将验证码保存到Redis中
        redisTemplate.opsForValue().set(emailOrPhone,String.valueOf(i));
        List<String> mail = new ArrayList<>();
        mail.add(emailOrPhone);
        String text = getCodeMail(i);
        mailUtil.sendMailMessage(mail, "您有一封来自 MetazzSpace 的回执！", text);
    }

    private String getCodeMail(int i) {
        String webName = "MetazzSpace";
        return String.format(MailUtil.mailText,
                webName,
                String.format(MailUtil.imMail, "Metazz"),
                "Metazz",
                String.format(MailUtil.userCodeFormat, i),
                "",
                webName);
    }

}
