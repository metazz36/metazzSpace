package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "标签修改DTO")
public class LabelModifyDTO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

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
