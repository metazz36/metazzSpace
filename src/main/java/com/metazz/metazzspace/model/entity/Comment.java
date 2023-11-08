package com.metazz.metazzspace.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 评论表
 */
@Data
public class Comment implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 对象ID(对应博客或说说或全站留言的ID)
     */
    private Integer objectId;

    /**
     * 评论类型(0-博客 1-说说 2-全站留言)
     */
    private String type;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 评论用户ID
     */
    private Integer fromUid;

    /**
     * 回复用户ID
     */
    private Integer toUid;

    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 评论点赞数
     */
    private Integer approvals;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

}