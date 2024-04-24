package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.enums.CommonEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.dto.LabelAddDTO;
import com.metazz.metazzspace.model.dto.LabelModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.model.entity.Label;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.mapper.LabelMapper;
import com.metazz.metazzspace.service.IBlogService;
import com.metazz.metazzspace.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    IBlogService blogService;

    @Override
    public void addLabel(LabelAddDTO labelAddDTO) {
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Label::getName, labelAddDTO.getName());
        List<Label> labels = labelMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(labels)){
            throw new BaseException(ExceptionEnum.LABEL_NAME_EXISTS);
        }
        labelMapper.insert(BeanUtil.toBean(labelAddDTO,Label.class));
    }

    @Override
    public Page<Label> getAllLabel(PageQueryDTO pageQueryDTO) {
        Page<Label> page = new Page<>(pageQueryDTO.getCurrent(), pageQueryDTO.getSize());
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Label::getSort);
        return labelMapper.selectPage(page,wrapper);
    }

    @Override
    public Label getLabelById(String id) {
        return labelMapper.selectById(id);
    }

    @Override
    public void deleteLabelById(String id) {
        Label label = labelMapper.selectById(id);
        if(!Optional.ofNullable(label).isPresent()){
            throw new BaseException(ExceptionEnum.LABEL_NOT_EXISTS);
        }
        // 该标签关联了博客，不允许删除
        Page<Blog> blogByPage = blogService.getBlogByPage(BlogQueryDTO.builder().current(1).size(5).labelId(Integer.valueOf(id)).categoryId(0).status(CommonEnum.ENABLE.getCode()).build());
        if(CollectionUtil.isNotEmpty(blogByPage.getRecords())){
            throw new BaseException(ExceptionEnum.LABEL_LINKED_BLOG);
        }
        labelMapper.deleteById(id);
    }

    @Override
    public void modifyLabel(LabelModifyDTO labelModifyDTO) {
        Label label = labelMapper.selectById(labelModifyDTO.getId());
        if(!Optional.ofNullable(label).isPresent()){
            throw new BaseException(ExceptionEnum.LABEL_NOT_EXISTS);
        }
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Label::getName, labelModifyDTO.getName());
        List<Label> labels = labelMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(labels)){
            if(labelModifyDTO.getId() != labels.get(0).getId()){
                throw new BaseException(ExceptionEnum.LABEL_NAME_EXISTS);
            }
        }
        labelMapper.updateById(BeanUtil.toBean(labelModifyDTO,Label.class));
    }

}
