package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.mapper.CommentMapper;
import com.metazz.metazzspace.model.dto.ChatAddDTO;
import com.metazz.metazzspace.model.dto.ChatModifyDTO;
import com.metazz.metazzspace.model.dto.ChatQueryDTO;
import com.metazz.metazzspace.model.entity.Chat;
import com.metazz.metazzspace.mapper.ChatMapper;
import com.metazz.metazzspace.model.entity.Comment;
import com.metazz.metazzspace.service.IChatService;
import com.metazz.metazzspace.service.IChatUserApplaudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

    @Autowired
    IChatUserApplaudService chatUserApplaudService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ChatMapper chatMapper;

    @Override
    public void addChat(ChatAddDTO chatAddDTO) {
        Chat chat = BeanUtil.toBean(chatAddDTO, Chat.class);
        this.save(chat);
    }

    @Override
    public void modifyChat(ChatModifyDTO chatModifyDTO) {
        if(!Optional.ofNullable(this.getById(chatModifyDTO.getId())).isPresent()){
            throw new BaseException(ExceptionEnum.CHAT_NOT_EXISTS);
        }
        this.updateById(BeanUtil.toBean(chatModifyDTO,Chat.class));
    }

    @Override
    public void deleteChat(String id) {
        if(!Optional.ofNullable(this.getById(id)).isPresent()){
            throw new BaseException(ExceptionEnum.CHAT_NOT_EXISTS);
        }
        // 1、删除说说(采用伪删除)
        Chat chat = new Chat();
        chat.setId(Integer.valueOf(id));
        chat.setStatus("0");
        chat.setDeleteTime(new Date());
        this.updateById(chat);
        // 2、删除关联数据 —— 用户点赞说说
        chatUserApplaudService.deleteByChatId(id);
        // 3、删除关联数据 —— 说说评论(采用伪删除)
        LambdaUpdateChainWrapper<Comment> commentUpdateWrapper = new LambdaUpdateChainWrapper<>(commentMapper);
        commentUpdateWrapper.
                eq(Comment::getType, CommonEnum.COMMENT_TYPE_CHAT.getCode()).
                eq(Comment::getObjectId,id).
                set(Comment::getStatus,CommonEnum.DISABLE.getCode()).
                set(Comment::getDeleteTime,new Date()).
                update();
    }

    @Override
    public Page<Chat> getChatByPage(ChatQueryDTO chatQueryDTO) {
        Page<Chat> page = new Page<>(chatQueryDTO.getCurrent(), chatQueryDTO.getSize());
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(chatQueryDTO.getStatus())){
            wrapper.eq(Chat::getStatus,chatQueryDTO.getStatus());
        }
        return chatMapper.selectPage(page, wrapper);
    }

    @Override
    public Chat getChatById(String id) {
        return chatMapper.selectById(id);
    }

}
