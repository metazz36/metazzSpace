package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(value = "博客查询DTO")
@Builder
public class BlogQueryDTO {

    @ApiModelProperty(value = "当前页")
    private int current;

    @ApiModelProperty(value = "每页的数量")
    private int size;

    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "博客分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "博客标签ID")
    private Integer labelId;

    @ApiModelProperty(value = "状态(0-无效 1-有效)")
    private String status;

}
