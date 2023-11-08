package com.metazz.metazzspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metazz.metazzspace.model.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}