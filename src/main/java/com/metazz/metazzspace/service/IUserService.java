package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.*;
import com.metazz.metazzspace.model.entity.User;

public interface IUserService extends IService<User> {

    void getCode(String email,String purpose);

    void registerUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    void exit();

    void modifyUser(UserModifyDTO userModifyDTO);

    void modifyPassword(ModifyPasswordDTO modifyPasswordDTO);

    void changeUserStatus(Integer userId, String status);

    void changeUserCommentStatus(Integer userId, String commentStatus);

    Page<User> getUserByPage(UserQueryDTO userQueryDTO);

    void applaudBlog(Integer blogId);

    void collectBlog(Integer blogId);

    void applaudChat(Integer chatId);

}
