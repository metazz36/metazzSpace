package com.metazz.metazzspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metazz.metazzspace.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}