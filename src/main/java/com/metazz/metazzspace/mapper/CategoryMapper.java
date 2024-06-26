package com.metazz.metazzspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metazz.metazzspace.model.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    void incrCount(@Param("id") Integer categoryId);

    void decrCount(@Param("id") Integer categoryId);

}