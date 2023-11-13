package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.dto.BlogDTO;
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
    public void addBlog(BlogDTO blogDTO) {
        // 标题唯一
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getTitle,blogDTO.getTitle());
        List<Blog> blogs = blogMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(blogs)){
            throw new BaseException(ExceptionEnum.BLOG_TITLE_EXISTS);
        }
        Blog blog = BeanUtil.toBean(blogDTO, Blog.class);
        blog.setAuthor("Metazz");
        blog.setUserId(0);

        // TODO 博客内容markdown格式怎么存,怎么展示  -->  待研究

        // TODO 计算markdown格式的博客的字数   -->  待研究

        blog.setWords(0);

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

}
