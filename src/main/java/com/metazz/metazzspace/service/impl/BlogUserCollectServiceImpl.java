package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.model.entity.BlogUserCollect;
import com.metazz.metazzspace.mapper.BlogUserCollectMapper;
import com.metazz.metazzspace.service.IBlogUserCollectService;
import org.springframework.stereotype.Service;

@Service
public class BlogUserCollectServiceImpl extends ServiceImpl<BlogUserCollectMapper, BlogUserCollect> implements IBlogUserCollectService {

    @Override
    public void deleteByBlogId(Integer blogId) {
        LambdaQueryWrapper<BlogUserCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogUserCollect::getBlogId,blogId);
        this.remove(wrapper);
    }

}
