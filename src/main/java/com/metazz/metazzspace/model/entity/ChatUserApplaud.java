package com.metazz.metazzspace.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户点赞说说记录表
 */
@Data
public class ChatUserApplaud implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 说说ID
     */
    private Integer blogId;

    /**
     * 创建时间
     */
    private Date createdTime;

}