package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.ChatAddDTO;
import com.metazz.metazzspace.model.dto.ChatModifyDTO;
import com.metazz.metazzspace.model.dto.ChatQueryDTO;
import com.metazz.metazzspace.model.entity.Chat;

public interface IChatService extends IService<Chat> {

    void addChat(ChatAddDTO chatAddDTO);

    void modifyChat(ChatModifyDTO chatModifyDTO);

    void deleteChat(String id);

    Page<Chat> getChatByPage(ChatQueryDTO chatQueryDTO);

    Chat getChatById(String id);

}
