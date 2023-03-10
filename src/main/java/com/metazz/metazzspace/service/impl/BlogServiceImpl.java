package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.dto.BlogDTO;
import com.metazz.metazzspace.entity.Blog;
import com.metazz.metazzspace.mapper.BlogMapper;
import com.metazz.metazzspace.service.IBlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Override
    public void addBlog(BlogDTO blogDTO) {
        this.save(BeanUtil.toBean(blogDTO,Blog.class));
    }

    @Override
    public void deleteBlog(String id) {

    }

    @Override
    public Blog getBlogById(String id) {
        return null;
    }

}
