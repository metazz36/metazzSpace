package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.BlogUserApplaud;
import com.metazz.metazzspace.mapper.BlogUserApplaudMapper;
import com.metazz.metazzspace.service.IBlogUserApplaudService;
import org.springframework.stereotype.Service;

@Service
public class BlogUserApplaudServiceImpl extends ServiceImpl<BlogUserApplaudMapper, BlogUserApplaud> implements IBlogUserApplaudService {

    @Override
    public void deleteByBlogId(Integer blogId) {
        LambdaQueryWrapper<BlogUserApplaud> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogUserApplaud::getBlogId,blogId);
        this.remove(wrapper);
    }

}
