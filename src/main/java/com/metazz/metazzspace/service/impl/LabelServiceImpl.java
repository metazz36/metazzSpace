package com.metazz.metazzspace.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.common.exception.BusinessException;
import com.metazz.metazzspace.model.dto.LabelDTO;
import com.metazz.metazzspace.model.entity.Label;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.mapper.LabelMapper;
import com.metazz.metazzspace.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Autowired
    LabelMapper labelMapper;

    @Override
    public void addLabel(LabelDTO labelDTO) {
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Label::getName,labelDTO.getName());
        List<Label> labels = labelMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(labels)){
            throw new BusinessException(ExceptionEnum.LABEL_NAME_EXISTS);
        }
        labelMapper.insert(BeanUtil.toBean(labelDTO,Label.class));
    }

    @Override
    public List<Label> getAllLabel() {
        return labelMapper.selectList(null);
    }

    @Override
    public Label getLabelById(String id) {
        return labelMapper.selectById(id);
    }

    @Override
    public void deleteLabelById(String id) {
        Label label = labelMapper.selectById(id);
        if(!Optional.ofNullable(label).isPresent()){
            throw new BusinessException(ExceptionEnum.LABEL_NOT_EXISTS);
        }
        labelMapper.deleteById(id);
    }

    @Override
    public void modifyLabel(LabelDTO labelDTO) {
        Label label = labelMapper.selectById(labelDTO.getId());
        if(!Optional.ofNullable(label).isPresent()){
            throw new BusinessException(ExceptionEnum.LABEL_NOT_EXISTS);
        }
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Label::getName,labelDTO.getName());
        List<Label> labels = labelMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(labels)){
            throw new BusinessException(ExceptionEnum.LABEL_NAME_EXISTS);
        }
        labelMapper.updateById(BeanUtil.toBean(labelDTO,Label.class));
    }

}
