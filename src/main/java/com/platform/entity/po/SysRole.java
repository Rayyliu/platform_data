package com.platform.entity.po;

import com.platform.entity.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysRole {

    private Long id;
    private String name;
    private String description ;

    /***
     * 角色--权限  关系；多对多
     */
    private List<SysRolePermission> rolePermission;


    /***
     * 角色--用户  关系；多对多
     */
//    private List<UserEntity> userEntity;


    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rolePermission=" + rolePermission +
                '}';
    }
}
