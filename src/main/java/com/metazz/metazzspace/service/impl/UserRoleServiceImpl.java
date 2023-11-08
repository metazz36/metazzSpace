package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.UserRole;
import com.metazz.metazzspace.mapper.UserRoleMapper;
import com.metazz.metazzspace.service.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
