package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "查询评论DTO")
public class CommentQueryDTO {

    /**
     * 评论类型(0-博客 1-说说 2-全站留言)
     */
    @NotBlank(message = "评论类型不能为空")
    @ApiModelProperty(value = "评论类型(0-博客 1-说说 2-全站留言)")
    private String type;

    /**
     * 对象ID(对应博客或说说或全站留言的ID)
     */
    @NotNull(message = "对象ID不能为空")
    @ApiModelProperty(value = "对象ID(对应博客或说说或全站留言的ID)")
    private Integer objectId;

    /**
     * 状态(0-无效 1-有效)
     */
    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "状态(0-无效 1-有效)")
    private String status;

}
