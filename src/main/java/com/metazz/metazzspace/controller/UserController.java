package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.ModifyPasswordDTO;
import com.metazz.metazzspace.model.dto.UserLoginDTO;
import com.metazz.metazzspace.model.dto.UserModifyDTO;
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
    public CR getCode(@RequestParam("email") String email,@RequestParam("purpose") String purpose){
        log.info("获取验证码: 邮箱:{}，用途:{}(0-用户注册，1-密码修改)",email,purpose);
        userService.getCode(email,purpose);
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

    @LoginCheck
    @PostMapping("/modifyUser")
    @ApiOperation(value = "用户信息修改", httpMethod = "POST")
    public CR modifyUser(@RequestBody UserModifyDTO userModifyDTO) {
        log.info("用户信息修改: {}",userModifyDTO);
        userService.modifyUser(userModifyDTO);
        return success();
    }

    @LoginCheck
    @PostMapping("/modifyPassword")
    @ApiOperation(value = "密码修改", httpMethod = "POST")
    public CR modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO) {
        log.info("密码修改: {}", modifyPasswordDTO);
        userService.modifyPassword(modifyPasswordDTO);
        return success();
    }

    @LoginCheck
    @GetMapping("/logout")
    @ApiOperation(value = "用户退出", httpMethod = "GET")
    public CR exit() {
        userService.exit();
        return success();
    }

}
