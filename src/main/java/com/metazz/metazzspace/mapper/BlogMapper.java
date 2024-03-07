package com.metazz.metazzspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    Page<Blog> queryBlogPage(@Param("dto") BlogQueryDTO blogQueryDTO, Page<Blog> page);

}