package com.platform.entity.po;

import com.platform.dal.model.platform.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class SysUserRolePO {

    private Long roleId;

    /***
     * 一个uid可能对应多个roleId
     */
    private List<com.platform.entity.po.SysRole> SysRoles;

    private int uid;

}
