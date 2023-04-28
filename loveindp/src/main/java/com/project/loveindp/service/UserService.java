package com.project.loveindp.service;

import com.project.loveindp.common.BusinessException;
import com.project.loveindp.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    UserModel getUser(Integer id);

    // 注册
    UserModel register(UserModel registerModel) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    // 登录
    UserModel login(String telphone,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

//    // 获取所有的用户数
    Integer countAllUser();
}
