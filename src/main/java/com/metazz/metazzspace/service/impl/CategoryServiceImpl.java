package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.dto.CategoryAddDTO;
import com.metazz.metazzspace.model.dto.CategoryModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.model.entity.Category;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.mapper.CategoryMapper;
import com.metazz.metazzspace.service.IBlogService;
import com.metazz.metazzspace.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    IBlogService blogService;

    @Override
    public Page<Category> getAllCategory(PageQueryDTO pageQueryDTO) {
        Page<Category> page = new Page<>(pageQueryDTO.getCurrent(), pageQueryDTO.getSize());
        return categoryMapper.selectPage(page,null);
    }

    @Override
    public void addCategory(CategoryAddDTO categoryAddDTO) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName, categoryAddDTO.getName());
        List<Category> categories = categoryMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(categories)){
            throw new BaseException(ExceptionEnum.CATEGORY_NAME_EXISTS);
        }
        categoryMapper.insert(BeanUtil.toBean(categoryAddDTO,Category.class));
    }

    @Override
    public void deleteCategoryById(String id) {
        Category category = categoryMapper.selectById(id);
        if(!Optional.ofNullable(category).isPresent()){
            throw new BaseException(ExceptionEnum.CATEGORY_NOT_EXISTS);
        }
        // 该分类关联了博客，不允许删除
        Page<Blog> blogByPage = blogService.getBlogByPage(BlogQueryDTO.builder().current(1).size(5).categoryId(Integer.valueOf(id)).labelId(0).status(CommonEnum.ENABLE.getCode()).build());
        if(CollectionUtil.isNotEmpty(blogByPage.getRecords())){
            throw new BaseException(ExceptionEnum.CATEGORY_LINKED_BLOG);
        }
        categoryMapper.deleteById(id);
    }

    @Override
    public void modifyCategory(CategoryModifyDTO categoryModifyDTO) {
        Category category = categoryMapper.selectById(categoryModifyDTO.getId());
        if(!Optional.ofNullable(category).isPresent()){
            throw new BaseException(ExceptionEnum.CATEGORY_NOT_EXISTS);
        }
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName, categoryModifyDTO.getName());
        List<Category> categories = categoryMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(categories)){
            if(categoryModifyDTO.getId() != categories.get(0).getId()){
                throw new BaseException(ExceptionEnum.CATEGORY_NAME_EXISTS);
            }
        }
        categoryMapper.updateById(BeanUtil.toBean(categoryModifyDTO,Category.class));
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryMapper.selectById(id);
    }

}
