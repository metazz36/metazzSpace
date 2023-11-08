package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.User;
import com.metazz.metazzspace.mapper.UserMapper;
import com.metazz.metazzspace.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
