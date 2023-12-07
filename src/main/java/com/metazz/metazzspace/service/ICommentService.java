package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.CommentAddDTO;
import com.metazz.metazzspace.model.dto.CommentQueryDTO;
import com.metazz.metazzspace.model.entity.Comment;
import com.metazz.metazzspace.model.vo.CommentQueryVO;

public interface ICommentService extends IService<Comment> {

    void addComment(CommentAddDTO commentAddDTO);

    CommentQueryVO queryComment(CommentQueryDTO commentQueryDTO);

}
