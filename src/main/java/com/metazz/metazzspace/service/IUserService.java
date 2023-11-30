package com.metazz.metazzspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.metazz.metazzspace.model.dto.ModifyPasswordDTO;
import com.metazz.metazzspace.model.dto.UserLoginDTO;
import com.metazz.metazzspace.model.dto.UserModifyDTO;
import com.metazz.metazzspace.model.dto.UserRegisterDTO;
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

}
