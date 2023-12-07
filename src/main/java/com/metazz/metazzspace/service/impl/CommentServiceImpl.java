package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.util.UserUtil;
import com.metazz.metazzspace.mapper.BlogMapper;
import com.metazz.metazzspace.mapper.ChatMapper;
import com.metazz.metazzspace.mapper.UserMapper;
import com.metazz.metazzspace.model.dto.CommentAddDTO;
import com.metazz.metazzspace.model.dto.CommentQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.model.entity.Chat;
import com.metazz.metazzspace.model.entity.Comment;
import com.metazz.metazzspace.mapper.CommentMapper;
import com.metazz.metazzspace.model.vo.CommentGroupVO;
import com.metazz.metazzspace.model.vo.CommentQueryVO;
import com.metazz.metazzspace.model.vo.CommentVO;
import com.metazz.metazzspace.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void addComment(CommentAddDTO commentAddDTO) {
        // 评论类型为博客
        if(CommonEnum.COMMENT_TYPE_BLOG.getCode().equals(commentAddDTO.getType())){
            LambdaQueryChainWrapper<Blog> blogWrapper = new LambdaQueryChainWrapper<>(blogMapper);
            Blog blog = blogWrapper.eq(Blog::getId, commentAddDTO.getObjectId()).one();
            if(!Optional.ofNullable(blog).isPresent()){
                // 博客不存在
                throw new BaseException(ExceptionEnum.BLOG_NOT_EXISTS);
            }
            if(CommonEnum.DISABLE.getCode().equals(blog.getStatus())){
                // 博客状态无效
                throw new BaseException(ExceptionEnum.BLOG_STATUS_DISABLE);
            }
            if(CommonEnum.NO.getCode().equals(blog.getOpenComment())){
                // 评论已关闭
                throw new BaseException(ExceptionEnum.COMMENT_IS_CLOSED);
            }
        }
        // 评论类型为说说
        if(CommonEnum.COMMENT_TYPE_CHAT.getCode().equals(commentAddDTO.getType())){
            LambdaQueryChainWrapper<Chat> chatWrapper = new LambdaQueryChainWrapper<>(chatMapper);
            Chat chat = chatWrapper.eq(Chat::getId, commentAddDTO.getObjectId()).one();
            if(!Optional.ofNullable(chat).isPresent()){
                // 说说不存在
                throw new BaseException(ExceptionEnum.CHAT_NOT_EXISTS);
            }
            if(CommonEnum.DISABLE.getCode().equals(chat.getStatus())){
                // 说说状态无效
                throw new BaseException(ExceptionEnum.CHAT_STATUS_DISABLE);
            }
            if(CommonEnum.NO.getCode().equals(chat.getOpenComment())){
                // 评论已关闭
                throw new BaseException(ExceptionEnum.COMMENT_IS_CLOSED);
            }
        }
        Comment comment = new Comment();
        comment.setContent(commentAddDTO.getContent());
        comment.setObjectId(commentAddDTO.getObjectId());
        comment.setType(commentAddDTO.getType());
        comment.setFromUid(UserUtil.getUser().getId());
        if(commentAddDTO.getToUid() != 0){
            comment.setToUid(commentAddDTO.getToUid());
        }
        if(commentAddDTO.getCommentId() != 0){
            comment.setCommentId(commentAddDTO.getCommentId());
        }
        save(comment);
    }

    @Override
    public CommentQueryVO queryComment(CommentQueryDTO commentQueryDTO) {
        // 评论类型为博客
        if(CommonEnum.COMMENT_TYPE_BLOG.getCode().equals(commentQueryDTO.getType())){
            LambdaQueryChainWrapper<Blog> blogWrapper = new LambdaQueryChainWrapper<>(blogMapper);
            Blog blog = blogWrapper.eq(Blog::getId, commentQueryDTO.getObjectId()).one();
            if(!Optional.ofNullable(blog).isPresent()){
                // 博客不存在
                throw new BaseException(ExceptionEnum.BLOG_NOT_EXISTS);
            }
            if(CommonEnum.DISABLE.getCode().equals(blog.getStatus())){
                // 博客状态无效
                throw new BaseException(ExceptionEnum.BLOG_STATUS_DISABLE);
            }
            if(CommonEnum.NO.getCode().equals(blog.getOpenComment())){
                // 评论已关闭
                throw new BaseException(ExceptionEnum.COMMENT_IS_CLOSED);
            }
        }
        // 评论类型为说说
        if(CommonEnum.COMMENT_TYPE_CHAT.getCode().equals(commentQueryDTO.getType())){
            LambdaQueryChainWrapper<Chat> chatWrapper = new LambdaQueryChainWrapper<>(chatMapper);
            Chat chat = chatWrapper.eq(Chat::getId, commentQueryDTO.getObjectId()).one();
            if(!Optional.ofNullable(chat).isPresent()){
                // 说说不存在
                throw new BaseException(ExceptionEnum.CHAT_NOT_EXISTS);
            }
            if(CommonEnum.DISABLE.getCode().equals(chat.getStatus())){
                // 说说状态无效
                throw new BaseException(ExceptionEnum.CHAT_STATUS_DISABLE);
            }
            if(CommonEnum.NO.getCode().equals(chat.getOpenComment())){
                // 评论已关闭
                throw new BaseException(ExceptionEnum.COMMENT_IS_CLOSED);
            }
        }
        // 所有评论
        List<Comment> comments = lambdaQuery().
                eq(Comment::getType, commentQueryDTO.getType()).
                eq(Comment::getObjectId, commentQueryDTO.getObjectId()).
                eq(Comment::getStatus,commentQueryDTO.getStatus()).
                orderByAsc(Comment::getCreatedTime).
                list();
        if(CollectionUtil.isEmpty(comments)){
            return new CommentQueryVO(null);
        }
        // 所有父评论
        List<Comment> FatherComments = comments.stream().filter(comment -> Objects.isNull(comment.getCommentId())).collect(Collectors.toList());
        // 评论组List
        ArrayList<CommentGroupVO> commentGroupVOS = new ArrayList<>();
        for (Comment fatherComment : FatherComments) {
            // 评论组
            CommentGroupVO commentGroupVO = new CommentGroupVO();
            // 评论组的父评论
            commentGroupVO.setFatherComment(buildCommentVO(fatherComment));
            // 评论组的子评论
            List<CommentVO> childComments = comments.stream().
                    filter(comment -> fatherComment.getId().equals(comment.getCommentId())).
                    map((comment) -> { return buildCommentVO(comment);}).
                    collect(Collectors.toList());
            commentGroupVO.setChildComments(childComments);
            commentGroupVOS.add(commentGroupVO);
        }
        return new CommentQueryVO(commentGroupVOS);
    }

    public CommentVO buildCommentVO(Comment comment){
        CommentVO commentVO = BeanUtil.toBean(comment, CommentVO.class);
        commentVO.setFromUserName(userMapper.selectById(commentVO.getFromUid()).getUserName());
        if(!Objects.isNull(commentVO.getToUid())){
            commentVO.setToUserName(userMapper.selectById(commentVO.getToUid()).getUserName());
        }
        return commentVO;
    }

}
