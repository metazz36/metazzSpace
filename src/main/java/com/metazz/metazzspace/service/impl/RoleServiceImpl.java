package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.Role;
import com.metazz.metazzspace.mapper.RoleMapper;
import com.metazz.metazzspace.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
