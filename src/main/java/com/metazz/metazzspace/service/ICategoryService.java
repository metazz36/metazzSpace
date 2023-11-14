package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.CategoryAddDTO;
import com.metazz.metazzspace.model.dto.CategoryModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.model.entity.Category;

public interface ICategoryService extends IService<Category> {

    Page<Category> getAllCategory(PageQueryDTO pageQueryDTO);

    void addCategory(CategoryAddDTO categoryAddDTO);

    void deleteCategoryById(String id);

    void modifyCategory(CategoryModifyDTO categoryModifyDTO);

    Category getCategoryById(String id);

}
