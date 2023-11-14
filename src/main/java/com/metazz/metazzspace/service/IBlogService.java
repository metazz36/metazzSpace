package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.BlogAddDTO;
import com.metazz.metazzspace.model.dto.BlogModifyDTO;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;

public interface IBlogService extends IService<Blog> {

    void addBlog(BlogAddDTO blogAddDTO);

    void deleteBlog(String id);

    Blog getBlogById(String id);

    Page<Blog> getBlogByPage(BlogQueryDTO blogQueryDTO);

    void modifyBlog(BlogModifyDTO blogModifyDTO);

}
