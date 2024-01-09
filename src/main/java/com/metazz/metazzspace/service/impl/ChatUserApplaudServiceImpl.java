package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.ChatUserApplaud;
import com.metazz.metazzspace.mapper.ChatUserApplaudMapper;
import com.metazz.metazzspace.service.IChatUserApplaudService;
import org.springframework.stereotype.Service;

@Service
public class ChatUserApplaudServiceImpl extends ServiceImpl<ChatUserApplaudMapper, ChatUserApplaud> implements IChatUserApplaudService {

    @Override
    public void deleteByChatId(String id) {
        LambdaQueryWrapper<ChatUserApplaud> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatUserApplaud::getChatId,id);
        this.remove(wrapper);
    }

}
