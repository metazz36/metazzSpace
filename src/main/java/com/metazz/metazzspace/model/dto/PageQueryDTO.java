package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询DTO")
public class PageQueryDTO {

    @ApiModelProperty(value = "当前页")
    private int current;

    @ApiModelProperty(value = "每页的数量")
    private int size;

}
