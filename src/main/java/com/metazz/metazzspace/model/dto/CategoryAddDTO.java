package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分类新增DTO")
public class CategoryAddDTO {

    /**
     * 分类名字
     */
    @ApiModelProperty(value = "分类名字")
    private String name;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

}
