package com.metazz.metazzspace.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 博客表
 */
@Data
public class Blog implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客简介
     */
    private String summary;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客图片
     */
    private String image;

    /**
     * 博客字数
     */
    private Integer words;

    /**
     * 博客分类ID
     */
    private Integer categoryId;

    /**
     * 博客分类名字
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 博客标签ID
     */
    private Integer labelId;

    /**
     * 博客标签名字
     */
    @TableField(exist = false)
    private String labelName;

    /**
     * 博客点赞数
     */
    private Integer approvals;

    /**
     * 博客收藏数
     */
    private Integer collections;

    /**
     * 博客评论数
     */
    private Integer comments;

    /**
     * 博客点击数
     */
    private Integer clicks;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 作者
     */
    private String author;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 文章出处
     */
    private String source;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 是否原创(0-否 1-是)
     */
    private String isOriginal;

    /**
     * 是否发布(0-否 1-是)
     */
    private String isPublish;

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