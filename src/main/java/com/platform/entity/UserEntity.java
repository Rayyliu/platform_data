package com.platform.entity;


import com.platform.entity.po.SysRole;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5528101080905698238L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 注册手机号
     */
    private String mobile;
    /**
     * 账户密码
     */
    private String password;
    /**
     * 账号邮箱
     */
    private String email;
    /**
     * 账号状态
     */
    private String status;

    /***
     * 用户名
     */
    private String username;


    /**
     * 注册角色
     */
    private List<SysRole> role;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SysRole> getRole() {
        return role;
    }

    public void setRole(List<SysRole> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
