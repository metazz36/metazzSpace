package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户登录DTO")
public class UserLoginDTO {

    /**
     * 用户名/邮箱
     */
    @ApiModelProperty(value = "用户名/邮箱")
    @NotBlank(message = "用户名/邮箱不能为空")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
