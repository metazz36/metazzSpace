package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ApiModel(value = "用户信息修改DTO")
public class UserModifyDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 性别(0-女 1-男)
     */
    @ApiModelProperty(value = "性别(0-女 1-男)")
    private String gender;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private Date birthday;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;

}
