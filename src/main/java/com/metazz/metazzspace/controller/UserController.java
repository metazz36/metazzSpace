package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.UserRegisterDTO;
import com.metazz.metazzspace.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户")
@SuppressWarnings("all")
public class UserController implements BaseController{

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public CR registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册: {}",userRegisterDTO);
        return BaseController.super.success();
    }

    @GetMapping("/getCode")
    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    public CR getCode(@RequestParam("emailOrPhone") String emailOrPhone){
        log.info("获取验证码: {}",emailOrPhone);
        userService.getCode(emailOrPhone);
        return success();
    }

}
