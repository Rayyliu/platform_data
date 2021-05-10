package com.platform.entity.po;


import lombok.Data;

import java.util.List;

@Data
public class SysRolePermission {

    private Integer permissionId;

    private Integer roleId;

    private List<SysPermission> permissions;
}
