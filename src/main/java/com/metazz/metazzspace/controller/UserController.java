package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.UserLoginDTO;
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

    @GetMapping("/getCode")
    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    public CR getCode(@RequestParam("email") String email){
        log.info("获取验证码: {}",email);
        userService.getCode(email);
        return success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public CR registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册: {}",userRegisterDTO);
        userService.registerUser(userRegisterDTO);
        return success();
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public CR loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录: {}",userLoginDTO);
        return success(userService.loginUser(userLoginDTO));
    }

}
