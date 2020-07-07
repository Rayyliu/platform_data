package com.platform.controller;

import com.platform.dal.mapper.platform.UserMapper;
import com.platform.dal.model.platform.User;
import com.platform.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册
     */
    @PostMapping("save")
    @ApiOperation("平台账户注册")
    public void register(@RequestBody User user){
        userService.save(user);
    }
}
