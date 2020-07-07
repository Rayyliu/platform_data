package com.platform.serviceimpl;

import com.platform.dal.mapper.platform.UserMapper;
import com.platform.dal.model.platform.User;
import com.platform.entity.UserEntity;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
}
