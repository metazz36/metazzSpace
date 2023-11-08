package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.dto.CategoryDTO;
import com.metazz.metazzspace.entity.Category;

import java.util.List;

public interface ICategoryService extends IService<Category> {

    List<Category> getAllCategory();

    void addCategory(CategoryDTO categoryDTO);

    void deleteCategoryById(String id);

    void modifyCategory(CategoryDTO categoryDTO);

    Category getCategoryById(String id);

}
