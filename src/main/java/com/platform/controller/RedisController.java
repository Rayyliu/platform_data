package com.platform.controller;


import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import com.platform.entity.UserEntity;
import com.platform.until.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis/")
@Transactional(rollbackFor = Exception.class)
public class RedisController {


    @Autowired
    RedisUtil redisUtil;

    @GetMapping("get")
    @ApiOperation("通过key获取redis")
    public ResponseResult getRedisValue(String redisKey) {
//        Object redisValue = redisUntil.getRedis(redisKey);
        Session se = SecurityUtils.getSecurityManager().getSession(new DefaultSessionKey(redisKey));
        Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
        UserEntity result = (UserEntity) coll.getPrimaryPrincipal();

        return new ResponseResult().success(ResultCode.SUCCESS.getCode(), true, "连接redis成功", result);
    }

    @GetMapping("set")
    @ApiOperation("通过key获取redis")
    public ResponseResult setRedisValue() {
        redisUtil.set("redis-001", "20210407-02");
        return new ResponseResult().success(ResultCode.SUCCESS.getCode(), true, "插入redis成功", null);
    }
}