package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "标签新增DTO")
public class LabelAddDTO {

    /**
     * 标签名字
     */
    @ApiModelProperty(value = "标签名字")
    private String name;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

}
