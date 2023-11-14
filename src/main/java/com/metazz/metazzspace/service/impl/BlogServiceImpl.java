package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.dto.BlogAddDTO;
import com.metazz.metazzspace.model.dto.BlogModifyDTO;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.mapper.BlogMapper;
import com.metazz.metazzspace.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogMapper blogMapper;

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
        blog.setUserId(0);
        this.save(blog);
    }

    @Override
    public void deleteBlog(String id) {
        Blog blog = blogMapper.selectById(id);
        if(!Optional.ofNullable(blog).isPresent()){
            throw new BaseException(ExceptionEnum.BLOG_NOT_EXISTS);
        }
        // TODO 删除关联数据(用户收藏博客,用户点赞博客,博客评论)
        blogMapper.deleteById(id);
    }

    @Override
    public Blog getBlogById(String id) {
        return blogMapper.selectById(id);
    }

    @Override
    public Page<Blog> getBlogByPage(BlogQueryDTO blogQueryDTO) {
        Page<Blog> page = new Page<>(blogQueryDTO.getCurrent(), blogQueryDTO.getSize());
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        if(0 != blogQueryDTO.getCategoryId()){
            wrapper.eq(Blog::getCategoryId,blogQueryDTO.getCategoryId());
        }
        if(0 != blogQueryDTO.getLabelId()){
            wrapper.eq(Blog::getLabelId,blogQueryDTO.getLabelId());
        }
        if(StrUtil.isNotBlank(blogQueryDTO.getTitle())){
            wrapper.like(Blog::getTitle,blogQueryDTO.getTitle());
        }
        return blogMapper.selectPage(page, wrapper);
    }

    @Override
    public void modifyBlog(BlogModifyDTO blogModifyDTO) {

    }

}
