package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "分类DTO")
public class CategoryDTO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 分类名字
     */
    private String name;

    /**
     * 排序字段
     */
    private Integer sort;

}
