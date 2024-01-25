package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.*;
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
public class UserController implements BaseController {

    @Autowired
    IUserService userService;

    @GetMapping("/getCode")
    @SysLog(operateName = "获取验证码")
    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    public CR getCode(@RequestParam("email") String email,@RequestParam("purpose") String purpose){
        log.info("获取验证码: 邮箱:{},用途:{}(0-用户注册，1-密码修改)",email,purpose);
        userService.getCode(email,purpose);
        return success();
    }

    @PostMapping("/register")
    @SysLog(operateName = "用户注册")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public CR registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册: {}",userRegisterDTO);
        userService.registerUser(userRegisterDTO);
        return success();
    }

    @PostMapping("/login")
    @SysLog(operateName = "用户登录")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public CR loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录: {}",userLoginDTO);
        return success(userService.loginUser(userLoginDTO));
    }

    @PostMapping("/modifyPassword")
    @SysLog(operateName = "密码修改")
    @ApiOperation(value = "密码修改", httpMethod = "POST")
    public CR modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO) {
        log.info("密码修改: {}", modifyPasswordDTO);
        userService.modifyPassword(modifyPasswordDTO);
        return success();
    }

    @LoginCheck
    @PostMapping("/modifyUser")
    @SysLog(operateName = "用户信息修改")
    @ApiOperation(value = "用户信息修改", httpMethod = "POST")
    public CR modifyUser(@RequestBody UserModifyDTO userModifyDTO) {
        log.info("用户信息修改: {}",userModifyDTO);
        userService.modifyUser(userModifyDTO);
        return success();
    }

    @LoginCheck
    @GetMapping("/logout")
    @SysLog(operateName = "用户退出")
    @ApiOperation(value = "用户退出", httpMethod = "GET")
    public CR exit() {
        userService.exit();
        return success();
    }

    @GetMapping("/changeUserStatus")
    @SysLog(operateName = "修改用户状态")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "修改用户状态", httpMethod = "GET")
    public CR changeUserStatus(@RequestParam("userId") Integer userId,@RequestParam("status") String status){
        log.info("修改用户状态: id:{},状态:{}(0-无效 1-有效)",userId,status);
        userService.changeUserStatus(userId,status);
        return success();
    }

    @GetMapping("/changeUserCommentStatus")
    @SysLog(operateName = "修改用户评论状态")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "修改用户评论状态", httpMethod = "GET")
    public CR changeUserCommentStatus(@RequestParam("userId") Integer userId,@RequestParam("commentStatus") String commentStatus){
        log.info("修改用户评论状态: id:{},状态:{}(0-禁言 1-正常)",userId,commentStatus);
        userService.changeUserCommentStatus(userId,commentStatus);
        return success();
    }

    @PostMapping("/getByPage")
    @SysLog(operateName = "分页查询用户信息")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "分页查询用户信息", httpMethod = "POST")
    public CR getUserByPage(@RequestBody UserQueryDTO userQueryDTO){
        return success(userService.getUserByPage(userQueryDTO));
    }

    @LoginCheck
    @GetMapping("/applaudBlog")
    @SysLog(operateName = "用户点赞博客")
    @ApiOperation(value = "用户点赞博客", httpMethod = "GET")
    public CR applaudBlog(@RequestParam("blogId") Integer blogId){
        userService.applaudBlog(blogId);
        return success();
    }

    @LoginCheck
    @GetMapping("/collectBlog")
    @SysLog(operateName = "用户收藏博客")
    @ApiOperation(value = "用户收藏博客", httpMethod = "GET")
    public CR collectBlog(@RequestParam("blogId") Integer blogId){
        userService.collectBlog(blogId);
        return success();
    }

    @LoginCheck
    @GetMapping("/applaudChat")
    @SysLog(operateName = "用户点赞说说")
    @ApiOperation(value = "用户点赞说说", httpMethod = "GET")
    public CR applaudChat(@RequestParam("chatId") Integer chatId){
        userService.applaudChat(chatId);
        return success();
    }

}
