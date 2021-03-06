package com.platform.controller;

import com.platform.dal.model.platform.User;
import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import com.platform.entity.StatusCode;
import com.platform.entity.UserEntity;
import com.platform.entity.po.LoginPO;
import com.platform.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user/")
@Transactional(rollbackFor = Exception.class)
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @Autowired
    ResponseResult responseResult;

    /**
     * 注册
     */
    @PostMapping("save")
    @ApiOperation("平台账户注册")
    public boolean register(@RequestBody User user){
        try {
            userService.save(user);
        }catch (Exception e){
            log.error("注册失败",e);
            return false;
        }
        return true;
//        String userId = userService.findByEmail(user.getEmail()).getEmail();
//        if(userId == null){
//            return responseResult.fail(StatusCode.ERROR,"注册失败",null);
//        }else {
//            return responseResult.success(StatusCode.OK,true,"注册成功",user);
//        }
    }

//    @GetMapping(value = "username/{userName}")
    @GetMapping(value = "username")
    @ApiOperation("通过用户名（email）查询用户信息")
    public UserEntity queryUser(@RequestParam("userName")String email){
        UserEntity userEntity = userService.findByEmail(email);
        return userEntity;

    }
//        @GetMapping(value = "username")
//        @ApiOperation("通过用户名（email）查询用户信息")
//        public UserEntity querUser(String email){
//            return userService.findByEmail(email);
//}

    @PostMapping("login")
    public Map<String,String> login(@RequestBody LoginPO loginPO
                      ) {

        //用户认证
        ResponseResult result =  userService.login(loginPO);
        System.out.println("result==="+result.toString());

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("code",result.getCode().toString());
        resultMap.put("sessionId",result.getData().toString());
        System.out.println("resultMap==="+resultMap);

//        if(result.getCode() == 0) {
//            return ResultCode.SUCCESS.getCode();
//        }else if(result.getCode() == 20004){
//            return ResultCode.USER_NOT_EXIST.getCode();
//        }else if(result.getCode() == 20002){
//            return ResultCode.USER_LOGIN_ERROR.getCode();
//        }
        return resultMap;
    }
}
