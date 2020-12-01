package com.platform.serviceimpl;

import com.platform.dal.mapper.platform.UserMapper;
import com.platform.dal.model.platform.User;
import com.platform.dal.model.platform.UserExample;
import com.platform.entity.UserEntity;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW) //propagation事务的传播行为，默认值为 Propagation.REQUIRED，如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }
}
