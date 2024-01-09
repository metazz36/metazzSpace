package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "说说新增DTO")
public class ChatAddDTO {

    /**
     * 说说内容
     */
    @ApiModelProperty(value = "说说内容")
    @NotBlank(message = "说说内容不能为空")
    private String content;


    /**
     * 是否开启评论(0-否 1-是)
     */
    @ApiModelProperty(value = "是否开启评论(0-否 1-是)")
    @NotBlank(message = "是否开启评论不能为空")
    private String openComment;

}
