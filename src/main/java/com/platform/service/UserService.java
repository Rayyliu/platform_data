package com.platform.service;

import com.platform.dal.model.platform.User;
import com.platform.entity.ResponseResult;
import com.platform.entity.UserEntity;
import com.platform.entity.po.LoginPO;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {

//    void save(UserEntity user);
    void save(User user);

    UserEntity findByEmail(String email);


    //用户认证
    ResponseResult login(LoginPO loginPO);


    //用户授权
    void authorization(UserEntity userEntity);

}
