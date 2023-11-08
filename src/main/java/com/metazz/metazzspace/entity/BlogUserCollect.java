package com.metazz.metazzspace.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户收藏博客记录表
 */
@Data
public class BlogUserCollect implements Serializable {
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
     * 博客ID
     */
    private Integer blogId;

    /**
     * 创建时间
     */
    private Date createdTime;

}