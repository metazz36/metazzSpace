package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.dto.BlogDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.mapper.BlogMapper;
import com.metazz.metazzspace.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public void addBlog(BlogDTO blogDTO) {
        this.save(BeanUtil.toBean(blogDTO,Blog.class));
    }

    @Override
    public void deleteBlog(String id) {

    }

    @Override
    public Blog getBlogById(String id) {
        return blogMapper.selectById(id);
    }

}
