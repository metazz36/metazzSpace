package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "博客DTO")
public class BlogDTO {

    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    @NotBlank(message = "博客标题不能为空")
    @Size(max = 200,message = "博客标题不能超过200字")
    private String title;

    /**
     * 博客简介
     */
    @ApiModelProperty(value = "博客简介")
    @NotNull(message = "博客简介不能为空")
    @Size(max = 200,message = "博客简介不能超过200字")
    private String summary;

    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容")
    @NotNull(message = "博客内容不能为空")
    private String content;

    /**
     * 博客图片
     */
    @ApiModelProperty(value = "博客图片")
    @NotNull(message = "博客图片不能为空")
    private String image;

    /**
     * 博客分类ID
     */
    @ApiModelProperty(value = "博客分类ID")
    @NotNull(message = "博客分类ID不能为空")
    private Integer categoryId;

    /**
     * 博客标签ID
     */
    @ApiModelProperty(value = "博客标签ID")
    @NotNull(message = "博客标签ID不能为空")
    private Integer labelId;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 是否发布(0-否 1-是)
     */
    @ApiModelProperty(value = "博客是否发布(0-否 1-是)")
    @NotNull(message = "博客是否发布不能为空")
    private String isPublish;

    /**
     * 是否开启评论(0-否 1-是)
     */
    @ApiModelProperty(value = "是否开启评论(0-否 1-是)")
    @NotNull(message = "是否开启评论不能为空")
    private String openComment;

}
