package com.platform.serviceimpl;

import com.platform.dal.mapper.platform.UserMapper;
import com.platform.dal.model.platform.User;
import com.platform.dal.model.platform.UserExample;
import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import com.platform.entity.UserEntity;
import com.platform.entity.po.LoginPO;
import com.platform.entity.po.SysPermission;
import com.platform.entity.po.SysRole;
import com.platform.entity.po.SysRolePermission;
import com.platform.service.UserService;
import com.platform.shiro.PlatformShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @Override
    public ResponseResult login(LoginPO loginPO) {

        PlatformShiroRealm platformShiroRealm = new PlatformShiroRealm();
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(platformShiroRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultWebSecurityManager); // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject(); // 获取当前主体

//        Session session= subject.getSession();
//        System.out.println(session);
//        session.setAttribute("userEntity",userEntity);
//        System.out.println(session);

//        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
//        String pwd =new Md5Hash(loginPO.getPassword(),"20210127anvhdie",2).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(loginPO.getUsername(), loginPO.getPassword());
        System.out.println("登录的账户名为==="+loginPO.getUsername());
        try {
            subject.login(token); // 登录,该步骤会去做认证处理
        } catch (UnknownAccountException e) {
            System.out.println("该账户不存在");
            return new ResponseResult(ResultCode.USER_NOT_EXIST.getCode(),ResultCode.USER_NOT_EXIST.getMessage(),20004);
        }catch (IncorrectCredentialsException ie){
            return new ResponseResult(ResultCode.USER_LOGIN_ERROR.getCode(),ResultCode.USER_LOGIN_ERROR.getMessage(),20002);
        }

            //获取sessionId
            String sid = (String) subject.getSession().getId();
            System.out.println("sessionId===" + sid);
            // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功，返回值取决subject.login(token)
            Boolean bs = subject.isAuthenticated();
            System.out.println("isAuthenticated:" + bs); // 输出true
            System.out.println("当前session为===" + subject.getSession());
            System.out.println("失效时间===" + subject.getSession().getTimeout());
            return new ResponseResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),sid);
        }


        @Override
        public void authorization (UserEntity userEntity){

            Subject subject = SecurityUtils.getSubject();
            userEntity.getRole().stream().forEach(sysRole -> subject.checkRole(sysRole.getName()));


            //查询用户对应的角色，用户->角色
            List<SysRole> lsr = userEntity.getRole().stream().map(role -> {
                SysRole sysRole = new SysRole();
                sysRole.setId(role.getId());
                sysRole.setName(role.getName());
                sysRole.setDescription(role.getDescription());
                sysRole.setRolePermission(role.getRolePermission());
                return sysRole;
            }).collect(Collectors.toList());


            //查询角色权限对应表关系，角色<->权限
            List<SysRolePermission> lsp = lsr.stream().map(sysRole -> {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRole.getRolePermission().stream().map(pers -> {
                    sysRolePermission.setPermissionId(pers.getPermissionId());
                    sysRolePermission.setRoleId(pers.getRoleId());
                    sysRolePermission.setPermissions(pers.getPermissions());
                    return sysRolePermission;
//                return new SysRolePermission(pers.getPermissionId(),pers.getRoleId(),pers.getPermissions());
                }).collect(Collectors.toList());
                return sysRolePermission;
            }).collect(Collectors.toList());

            System.out.println("List<SysRolePermission>===" + lsp);


            //查询角色对应的所有权限，角色权限对应关系->所拥有权限
            List<SysPermission> per = lsp.stream().map(sp -> {
                SysPermission sysPermission = new SysPermission();
                sp.getPermissions().stream().map(pers -> {
                    sysPermission.setName(pers.getName());
                    sysPermission.setUrl(pers.getUrl());
                    sysPermission.setDescription(pers.getDescription());
                    sysPermission.setId(pers.getId());
                    return sysPermission;
                }).collect(Collectors.toList());
                return sysPermission;
            }).collect(Collectors.toList());
//        })
            System.out.println("per===" + per);
            per.stream().forEach(p -> subject.checkPermission(p.getName()));
        }

    }

