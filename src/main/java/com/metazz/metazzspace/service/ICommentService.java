package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.CommentAddDTO;
import com.metazz.metazzspace.model.entity.Comment;

public interface ICommentService extends IService<Comment> {

    void addComment(CommentAddDTO commentAddDTO);

}
