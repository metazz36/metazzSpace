package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "说说查询DTO")
public class ChatQueryDTO {

    @ApiModelProperty(value = "当前页")
    private int current;

    @ApiModelProperty(value = "每页的数量")
    private int size;

    @ApiModelProperty(value = "状态(0-无效 1-有效)")
    private String status;

}
