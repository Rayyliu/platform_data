package com.platform.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.dal.mapper.platform.UserMapper;
import com.platform.dal.model.platform.User;
import com.platform.entity.UserEntity;
import com.platform.service.ShiroService;
import com.platform.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PlatformShiroRealm extends AuthorizingRealm {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Resource
    UserMapper userMapper;

    @Autowired
    ShiroService shiroService;

    @Autowired
    HashedCredentialsMatcher hashedCredentialsMatcher;


//    public void setName(String name){
//        super.setName("platformShiroRealm");
//    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("=====进入授权=====");

        //获取当前登录用户
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User)subject.getPrincipal();

        UserEntity  userName = (UserEntity)principals.getPrimaryPrincipal();
        User user = userMapper.selectUserByUserName(userName.getUsername());
//        ObjectMapper objectMapper = new ObjectMapper();
//        UserEntity user = objectMapper.convertValue(ob,UserEntity.class);
        //能进入到该方法说明用户已经通过认证
        System.out.println("用户"+user.getEmail()+"已完成认证，接下来进行权限管理校验");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Map<String,List>> roles = shiroService.getRoles(user.getId());
//        for(Map<String,List> roleMap:roles){
//            List nameList = roleMap.get("name");
//            simpleAuthorizationInfo.addRoles(nameList);
//        }

        //get("name"):表示取sys_role表的name字段值作匹配
        roles.stream().forEach(item->simpleAuthorizationInfo.addRoles(item.get("name")));
//        roles.stream().forEach(item->simpleAuthorizationInfo.addRoles(item.get("url")));
        log.info("simpleAuthorizationInfo==="+simpleAuthorizationInfo);
//        simpleAuthorizationInfo.addRole();
//        userEntity.getRole().stream().map(role->role.getName()).collect(Collectors.toList());

        return simpleAuthorizationInfo;
    }




    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("=====进入认证=====");
        // 1.获取用户信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String  username = usernamePasswordToken.getUsername();
        System.out.println("username==="+username);
        // 2.通过用户名到数据库中获取凭证
//        String pwd = userMapper.selectUserByEmail(userName).getPassword();
//        UserEntity userInfo = userMapper.selectUserByEmail(userName);
        User userInfo = userMapper.selectUserByUserName(username);
        if(userInfo == null){
            return null;
        }
//        String pwd= usernamePasswordToken.getPassword().toString();
        String st = userInfo.getSalt();
        String signPwd = null;
        if("md5".equals(hashedCredentialsMatcher.getHashAlgorithmName())){
            signPwd = new Md5Hash(usernamePasswordToken.getPassword(), st, hashedCredentialsMatcher.getHashIterations()).toString();
        }
//        if(!signPwd.equals(userInfo.getPassword())){
////            return null;
////        }
        String salt = userMapper.selectUserByUserName(username).getSalt();
        //getName()=com.platform.shiro.PlatformShiroRealm_0
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userInfo.getUsername());
        userEntity.setUserId(Integer.toString(userInfo.getId()));
        userEntity.setMobile(userInfo.getMobile());
        userEntity.setEmail(userInfo.getEmail());
        userEntity.setStatus(userInfo.getStatus());
//        userEntity.setRole(userInfo.getRole());
        userEntity.setPassword(new Md5Hash(userInfo.getPassword(), new PlatformSimpleByteSource(st), hashedCredentialsMatcher.getHashIterations()).toString());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(), new PlatformSimpleByteSource(salt), getName());
        return simpleAuthenticationInfo;
    }


//    /**
//     * 重写方法,清除当前用户的的 授权缓存
//     * @param
//     */
//    public void clearCachedAuthorizationInfo() {
//        super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//    }
//
//
//    /**
//     * 重写方法，清除当前用户的 认证缓存
//     * @param
//     */
//    public void clearCachedAuthenticationInfo() {
//        super.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
//    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
