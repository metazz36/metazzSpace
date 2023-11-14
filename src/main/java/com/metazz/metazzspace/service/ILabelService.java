package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.LabelAddDTO;
import com.metazz.metazzspace.model.dto.LabelModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.model.entity.Label;

public interface ILabelService extends IService<Label> {

    void addLabel(LabelAddDTO labelAddDTO);

    Page<Label> getAllLabel(PageQueryDTO pageQueryDTO);

    Label getLabelById(String id);

    void deleteLabelById(String id);

    void modifyLabel(LabelModifyDTO labelModifyDTO);

}
