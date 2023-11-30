package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(value = "用户查询DTO")
@Builder
public class UserQueryDTO {

    @ApiModelProperty(value = "当前页")
    private int current;

    @ApiModelProperty(value = "每页的数量")
    private int size;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "状态(0-无效 1-有效)")
    private String status;

    @ApiModelProperty(value = "评论状态(0-禁言 1-正常)")
    private String commentStatus;

}
