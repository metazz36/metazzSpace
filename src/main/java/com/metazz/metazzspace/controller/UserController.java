package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.UserRegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户")
@SuppressWarnings("all")
public class UserController implements BaseController{

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public CR registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册: {}",userRegisterDTO);
        return BaseController.super.success();
    }

}
