package com.platform.controller;


import com.platform.dal.mapper.platform.SysRoleMapper;
import com.platform.dal.mapper.platform.SysUserRoleMapper;
import com.platform.dal.mapper.platform.UserMapper;
import com.platform.entity.UserEntity;
import com.platform.entity.po.SysPermission;
import com.platform.entity.po.SysRole;
import com.platform.entity.po.SysRolePermission;
import com.platform.entity.po.SysUserRolePO;
import com.platform.service.ShiroService;
import com.platform.service.UserService;
import com.platform.shiro.PlatformShiroRealm;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shiro/ignore/")
public class ShiroController {


    @Autowired
    ShiroService shiroService;


    @Autowired
    UserService userService;

//    @Autowired
//    SysRoleMapper sysRoleMapper;



    @PostMapping("search")
    public List getRoles(int uid){
        List<Map<String,List>> rm =   shiroService.getRoles(uid);
//        System.out.println("rm=="+rm);
        List names =  rm.stream().map(item->item.get("name")).collect(Collectors.toList());
        List urls =  rm.stream().map(item->item.get("url")).collect(Collectors.toList());
        System.out.println("rmName==="+names);
        System.out.println("rmUrl"+urls);
        return rm;
            }


//    @PostMapping("login")
//    public void login(@RequestBody UserEntity userEntity
//    ) {
//        //用户认证
//        userService.login(userEntity);
//    }

//        @PostMapping("authentication")
//    public void authenticationTest(
//            @RequestBody UserEntity userEntity
//    ){
//        //用户认证
//        if(userService.login(userEntity)) {
//            userService.authorization(userEntity);
//        }
//        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
//        {
//
////            TextConfigurationRealm realm=new TextConfigurationRealm();
////            realm.setRolePermissionResolver();
//            simpleAccountRealm.addAccount("liurui","123456","admin","user");
//        }
//        PlatformShiroRealm platformShiroRealm = new PlatformShiroRealm();
//
//        // 1.构建SecurityManager环境
////        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//////        defaultSecurityManager.setRealm(platformShiroRealm);
//        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(platformShiroRealm);
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(platformShiroRealm);
//
//        // 2.主体提交认证请求
//        SecurityUtils.setSecurityManager(defaultWebSecurityManager); // 设置SecurityManager环境
//        Subject subject = SecurityUtils.getSubject(); // 获取当前主体
//
//        Session session= subject.getSession();
//        System.out.println(session);
//        session.setAttribute("userEntity",userEntity);
//        System.out.println(session);
//
////        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
//        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
//        subject.login(token); // 登录,该步骤回去做认证处理
//
//        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功，返回值取决subject.login(token)
//        Boolean bs = subject.isAuthenticated();
//        System.out.println("isAuthenticated:" + bs); // 输出true


            // 判断subject是否具有admin和user两个角色权限,如没有则会报错
//        subject.checkRoles("admin","user");//执行权限管理

//            userEntity.getRole().stream().forEach(sysRole -> subject.checkRole(sysRole.getName()));
//
//
//            //查询用户对应的角色，用户->角色
//            List<SysRole> lsr = userEntity.getRole().stream().map(role -> {
//                SysRole sysRole = new SysRole();
//                sysRole.setId(role.getId());
//                sysRole.setName(role.getName());
//                sysRole.setDescription(role.getDescription());
//                sysRole.setRolePermission(role.getRolePermission());
//                return sysRole;
//            }).collect(Collectors.toList());
//
//
//            //查询角色权限对应表关系，角色<->权限
//            List<SysRolePermission> lsp = lsr.stream().map(sysRole -> {
//                SysRolePermission sysRolePermission = new SysRolePermission();
//                sysRole.getRolePermission().stream().map(pers -> {
//                    sysRolePermission.setPermissionId(pers.getPermissionId());
//                    sysRolePermission.setRoleId(pers.getRoleId());
//                    sysRolePermission.setPermissions(pers.getPermissions());
//                    return sysRolePermission;
////                return new SysRolePermission(pers.getPermissionId(),pers.getRoleId(),pers.getPermissions());
//                }).collect(Collectors.toList());
//                return sysRolePermission;
//            }).collect(Collectors.toList());
//
//            System.out.println("List<SysRolePermission>===" + lsp);
//
//
//            //查询角色对应的所有权限，角色权限对应关系->所拥有权限
//            List<SysPermission> per = lsp.stream().map(sp -> {
//                SysPermission sysPermission = new SysPermission();
//                sp.getPermissions().stream().map(pers -> {
//                    sysPermission.setName(pers.getName());
//                    sysPermission.setUrl(pers.getUrl());
//                    sysPermission.setDescription(pers.getDescription());
//                    sysPermission.setId(pers.getId());
//                    return sysPermission;
//                }).collect(Collectors.toList());
//                return sysPermission;
//            }).collect(Collectors.toList());
////        })
//            System.out.println("per===" + per);
//            per.stream().forEach(p -> subject.checkPermission(p.getName()));
//
//
//            //权限校验
//        subject.checkPermission("userInfo:add");
    }
//}


