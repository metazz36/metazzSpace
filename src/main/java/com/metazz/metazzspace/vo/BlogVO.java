package com.metazz.metazzspace.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "博客VO")
public class BlogVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    private String title;

    /**
     * 博客简介
     */
    @ApiModelProperty(value = "博客简介")
    private String summary;

    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    private String content;

    /**
     * 博客图片
     */
    @ApiModelProperty(value = "博客图片")
    private String image;

    /**
     * 博客字数
     */
    @ApiModelProperty(value = "博客字数")
    private Integer words;

    /**
     * 博客分类ID
     */
    @ApiModelProperty(value = "博客分类ID")
    private Integer categoryId;

    /**
     * 博客标签ID
     */
    @ApiModelProperty(value = "博客标签ID")
    private Integer labelId;

    /**
     * 博客点赞数
     */
    @ApiModelProperty(value = "博客点赞数")
    private Integer approvals;

    /**
     * 博客收藏数
     */
    @ApiModelProperty(value = "博客收藏数")
    private Integer collections;

    /**
     * 博客评论数
     */
    @ApiModelProperty(value = "博客评论数")
    private Integer comments;

    /**
     * 博客点击数
     */
    @ApiModelProperty(value = "博客点击数")
    private Integer clicks;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 文章出处
     */
    @ApiModelProperty(value = "文章出处")
    private String source;

    /**
     * 是否原创(0-否 1-是)
     */
    @ApiModelProperty(value = "是否原创(0-否 1-是)")
    private String isOriginal;

    /**
     * 是否发布(0-否 1-是)
     */
    @ApiModelProperty(value = "博客是否发布(0-否 1-是)标题")
    private String isPublish;

    /**
     * 是否开启评论(0-否 1-是)
     */
    @ApiModelProperty(value = "是否开启评论(0-否 1-是)")
    private String openComment;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

}
