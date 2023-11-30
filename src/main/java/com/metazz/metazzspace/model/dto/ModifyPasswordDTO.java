package com.metazz.metazzspace.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "密码修改DTO")
public class ModifyPasswordDTO {

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

}
