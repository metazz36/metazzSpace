package com.metazz.metazzspace.model.dto;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "说说修改DTO")
public class ChatModifyDTO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @NotNull(message = "主键ID不能为空")
    private Integer id;

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
