package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "发表评论DTO")
public class CommentAddDTO {

    /**
     * 评论类型(0-博客 1-说说 2-全站留言)
     */
    @ApiModelProperty(value = "评论类型(0-博客 1-说说 2-全站留言)")
    private String type;

    /**
     * 对象ID(对应博客或说说或全站留言的ID)
     */
    @ApiModelProperty(value = "对象ID(对应博客或说说或全站留言的ID)")
    private Integer objectId;

    /**
     * 评论ID(父评论传0)
     */
    @ApiModelProperty(value = "评论ID(父评论传0)")
    private Integer commentId;

    /**
     * 回复用户ID(父评论传0)
     */
    @ApiModelProperty(value = "回复用户ID(父评论传0)")
    private Integer toUid;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

}
