package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.mapper.CategoryMapper;
import com.metazz.metazzspace.mapper.CommentMapper;
import com.metazz.metazzspace.mapper.LabelMapper;
import com.metazz.metazzspace.model.dto.BlogAddDTO;
import com.metazz.metazzspace.model.dto.BlogModifyDTO;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.mapper.BlogMapper;
import com.metazz.metazzspace.model.entity.Comment;
import com.metazz.metazzspace.service.IBlogService;
import com.metazz.metazzspace.service.IBlogUserApplaudService;
import com.metazz.metazzspace.service.IBlogUserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    IBlogUserCollectService blogUserCollectService;

    @Autowired
    IBlogUserApplaudService blogUserApplaudService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    LabelMapper labelMapper;

    @Override
    public void addBlog(BlogAddDTO blogAddDTO) {
        // 标题唯一
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getTitle, blogAddDTO.getTitle());
        List<Blog> blogs = blogMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(blogs)){
            throw new BaseException(ExceptionEnum.BLOG_TITLE_EXISTS);
        }
        Blog blog = BeanUtil.toBean(blogAddDTO, Blog.class);
        blog.setAuthor("Metazz");
        blog.setUserId(1);
        blog.setIsOriginal("1");
        blog.setSource("原创");
        this.save(blog);
        // 对应分类博客数量+1
        categoryMapper.incrCount(blogAddDTO.getCategoryId());
        // 对应标签博客数量+1
        labelMapper.incrCount(blogAddDTO.getLabelId());
    }

    @Override
    public void deleteBlog(String id) {
        Blog blog = lambdaQuery().
                eq(Blog::getId, Integer.valueOf(id)).
                eq(Blog::getStatus, CommonEnum.ENABLE.getCode()).
                one();
        if(!Optional.ofNullable(blog).isPresent()){
            throw new BaseException(ExceptionEnum.BLOG_NOT_EXISTS);
        }
        // 1、删除博客(采用伪删除)
        lambdaUpdate().
                set(Blog::getStatus,CommonEnum.DISABLE.getCode()).
                set(Blog::getDeleteTime,new Date()).
                eq(Blog::getId, Integer.valueOf(id)).
                update();
        // 2、删除关联数据 —— 用户收藏博客
        blogUserCollectService.deleteByBlogId(Integer.valueOf(id));
        // 3、删除关联数据 —— 用户点赞博客
        blogUserApplaudService.deleteByBlogId(Integer.valueOf(id));
        // 4、删除关联数据 —— 博客评论(采用伪删除)
        LambdaUpdateChainWrapper<Comment> commentUpdateWrapper = new LambdaUpdateChainWrapper<>(commentMapper);
        commentUpdateWrapper.
                eq(Comment::getType, CommonEnum.COMMENT_TYPE_BLOG.getCode()).
                eq(Comment::getObjectId,id).
                set(Comment::getStatus,CommonEnum.DISABLE.getCode()).
                set(Comment::getDeleteTime,new Date()).
                update();
        // 5、对应分类博客数量-1
        categoryMapper.decrCount(blog.getCategoryId());
        // 6、对应标签博客数量-1
        labelMapper.decrCount(blog.getLabelId());
    }

    @Override
    public Blog getBlogById(String id) {
        return blogMapper.selectById(id);
    }

    @Override
    public Page<Blog> getBlogByPage(BlogQueryDTO blogQueryDTO) {
        Page<Blog> page = new Page<>(blogQueryDTO.getCurrent(), blogQueryDTO.getSize());
        Page<Blog> blogPage = blogMapper.queryBlogPage(blogQueryDTO,page);
        return blogPage;
    }

    @Override
    public void modifyBlog(BlogModifyDTO blogModifyDTO) {
        Blog blog = lambdaQuery().
                eq(Blog::getId, blogModifyDTO.getId()).
                eq(Blog::getStatus, CommonEnum.ENABLE.getCode()).
                one();
        if(!Optional.ofNullable(blog).isPresent()){
            throw new BaseException(ExceptionEnum.BLOG_NOT_EXISTS);
        }
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getTitle,blogModifyDTO.getTitle());
        List<Blog> blogs = blogMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(blogs)){
            if(blogModifyDTO.getId() != blogs.get(0).getId()){
                throw new BaseException(ExceptionEnum.BLOG_TITLE_EXISTS);
            }
        }
        blogMapper.updateById(BeanUtil.toBean(blogModifyDTO,Blog.class));
        // 若修改了分类，调整分类对应博客数量
        if(!blog.getCategoryId().equals(blogModifyDTO.getCategoryId())){
            // 原分类博客数量-1
            categoryMapper.decrCount(blog.getCategoryId());
            // 现分类博客数量-1
            categoryMapper.incrCount(blogModifyDTO.getCategoryId());
        }
        // 若修改了标签，调整标签对应博客数量
        if(!blog.getLabelId().equals(blogModifyDTO.getLabelId())){
            // 原标签博客数量-1
            labelMapper.decrCount(blog.getLabelId());
            // 现标签博客数量-1
            labelMapper.incrCount(blogModifyDTO.getLabelId());
        }
    }

}
