package com.metazz.metazzspace.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel(value = "评论VO")
public class CommentVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 对象ID(对应博客或说说或全站留言的ID)
     */
    @ApiModelProperty(value = "对象ID(对应博客或说说或全站留言的ID)")
    private Integer objectId;

    /**
     * 评论类型(0-博客 1-说说 2-全站留言)
     */
    @ApiModelProperty(value = "评论类型(0-博客 1-说说 2-全站留言)")
    private String type;

    /**
     * 状态(0-无效 1-有效)
     */
    @ApiModelProperty(value = "状态(0-无效 1-有效)")
    private String status;

    /**
     * 评论用户
     */
    @ApiModelProperty(value = "评论用户ID")
    private Integer fromUid;

    /**
     * 评论用户名称
     */
    @ApiModelProperty(value = "评论用户名称")
    private String fromUserName;

    /**
     * 回复用户ID
     */
    @ApiModelProperty(value = "回复用户ID")
    private Integer toUid;

    /**
     * 回复用户名称
     */
    @ApiModelProperty(value = "回复用户名称")
    private String toUserName;

    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    private Integer commentId;

    /**
     * 评论点赞数
     */
    @ApiModelProperty(value = "评论点赞数")
    private Integer approvals;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;


}
