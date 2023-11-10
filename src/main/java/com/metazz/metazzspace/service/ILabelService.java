package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.LabelDTO;
import com.metazz.metazzspace.model.dto.PageDTO;
import com.metazz.metazzspace.model.entity.Label;

import java.util.List;

public interface ILabelService extends IService<Label> {

    void addLabel(LabelDTO labelDTO);

    Page<Label> getAllLabel(PageDTO pageDTO);

    Label getLabelById(String id);

    void deleteLabelById(String id);

    void modifyLabel(LabelDTO labelDTO);

}
