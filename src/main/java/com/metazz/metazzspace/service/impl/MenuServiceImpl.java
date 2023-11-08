package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.Menu;
import com.metazz.metazzspace.mapper.MenuMapper;
import com.metazz.metazzspace.service.IMenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
