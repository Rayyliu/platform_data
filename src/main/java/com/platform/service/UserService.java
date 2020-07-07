package com.platform.service;

import com.platform.dal.model.platform.User;
import com.platform.entity.UserEntity;
import org.springframework.stereotype.Service;

public interface UserService {

//    void save(UserEntity user);
    void save(User user);

}
