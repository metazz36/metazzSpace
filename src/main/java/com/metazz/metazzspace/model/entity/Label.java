package com.metazz.metazzspace.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 博客标签表
 */
@Data
public class Label implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名字
     */
    private String name;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 标签点击数
     */
    private Integer clicks;

    /**
     * 博客数量
     */
    private Integer counts;

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