package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.dto.CategoryDTO;
import com.metazz.metazzspace.model.entity.Category;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.mapper.CategoryMapper;
import com.metazz.metazzspace.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.selectList(null);
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName,categoryDTO.getName());
        List<Category> categories = categoryMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(categories)){
            throw new BaseException(ExceptionEnum.CATEGORY_NAME_EXISTS);
        }
        categoryMapper.insert(BeanUtil.toBean(categoryDTO,Category.class));
    }

    @Override
    public void deleteCategoryById(String id) {
        Category category = categoryMapper.selectById(id);
        if(!Optional.ofNullable(category).isPresent()){
            throw new BaseException(ExceptionEnum.CATEGORY_NOT_EXISTS);
        }
        categoryMapper.deleteById(id);
    }

    @Override
    public void modifyCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.selectById(categoryDTO.getId());
        if(!Optional.ofNullable(category).isPresent()){
            throw new BaseException(ExceptionEnum.CATEGORY_NOT_EXISTS);
        }
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName,categoryDTO.getName());
        List<Category> categories = categoryMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(categories)){
            throw new BaseException(ExceptionEnum.CATEGORY_NAME_EXISTS);
        }
        categoryMapper.updateById(BeanUtil.toBean(categoryDTO,Category.class));
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryMapper.selectById(id);
    }

}
