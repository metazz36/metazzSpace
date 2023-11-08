package com.metazz.metazzspace.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "标签DTO")
public class LabelDTO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 标签名字
     */
    private String name;

    /**
     * 排序字段
     */
    private Integer sort;

}
