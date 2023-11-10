package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.CategoryDTO;
import com.metazz.metazzspace.model.dto.PageDTO;
import com.metazz.metazzspace.model.entity.Category;

import java.util.List;

public interface ICategoryService extends IService<Category> {

    Page<Category> getAllCategory(PageDTO pageDTO);

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategoryById(String id);

    void modifyCategory(CategoryDTO categoryDTO);

    Category getCategoryById(String id);

}
