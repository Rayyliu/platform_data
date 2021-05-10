package com.platform.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

public class PlatformRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        Permission[] permissions ;

        /***
         * 作用：不同角色配置不同的权限，角色信息存在sys_role表
         * eg.角色如果是admin，则获取的所有权限
         * eg.角色如果是user，则获得path=userInfo:*的所有权限
         * eg.如果匹配不上，则不会授权任何权限
         * 扩展：可以根据该配置，配置不同项目组的访问权限。eg.供应链测试人员只有访问path=supplychain：*的权限，若想访问alchemist项目则需要在数据sys_role表里配置相应角色
         */
        if("admin".equals(roleString)){
            //AllPermission表示具有所有的权限
            permissions = new Permission[]{new AllPermission()};
        }else if("user".equals(roleString)){
            permissions = new Permission[]{new WildcardPermission("userInfo:*")};
        }else {
            return null;
        }


        return Arrays.asList(permissions);
    }
}
