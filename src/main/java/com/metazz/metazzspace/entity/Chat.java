package com.metazz.metazzspace.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 说说表
 */
@Data
public class Chat implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 说说内容
     */
    private String content;

    /**
     * 说说评论数
     */
    private Integer comments;

    /**
     * 说说点赞数
     */
    private Integer approvals;

    /**
     * 说说点击数
     */
    private Integer clicks;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 是否开启评论(0-否 1-是)
     */
    private String openComment;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

}