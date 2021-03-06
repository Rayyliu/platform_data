package com.platform.dal.mapper.platform;

import com.platform.dal.model.platform.SysRolePermission;
import com.platform.dal.model.platform.SysRolePermissionExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface SysRolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    long countByExample(SysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    int deleteByExample(SysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    int insert(SysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    int insertSelective(SysRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    List<SysRolePermission> selectByExample(SysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Wed Jan 20 11:13:55 CST 2021
     */
    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);


    @Select("select permission_id,role_id from sys_role_permission where role_id = #{role_id}")
    @Results({
            @Result(id=true,column="permission_id",property="permissionId"),
            @Result(column="permission_id",property="permissions",many = @Many(select = "com.platform.dal.mapper.platform.SysPermissionMapper.getPermission",fetchType = FetchType.LAZY)),
            @Result(column="role_id",property="roleId"),
    })
    com.platform.entity.po.SysRolePermission getRolePermission(int roleId);
}