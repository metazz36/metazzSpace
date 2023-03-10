package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.dto.BlogDTO;
import com.metazz.metazzspace.entity.Blog;

public interface IBlogService extends IService<Blog> {

    /**
     * 新增博客
     *
     * @param blogDTO 博客DTO
     * @return
     */
    void addBlog(BlogDTO blogDTO);

    /**
     * 删除博客
     *
     * @param id 博客id
     * @return
     */
    void deleteBlog(String id);

    /**
     * 根据id查询博客
     *
     * @param id 博客id
     * @return Blog
     */
    Blog getBlogById(String id);
}
