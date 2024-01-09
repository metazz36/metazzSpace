package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.entity.ChatUserApplaud;

public interface IChatUserApplaudService extends IService<ChatUserApplaud> {

    void deleteByChatId(String id);

}
