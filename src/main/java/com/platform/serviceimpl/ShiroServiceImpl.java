package com.platform.serviceimpl;

import com.platform.dal.mapper.platform.SysUserRoleMapper;
import com.platform.entity.po.SysPermission;
import com.platform.entity.po.SysRole;
import com.platform.entity.po.SysRolePermission;
import com.platform.entity.po.SysUserRolePO;
import com.platform.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<Map<String,List>> getRoles(int uid) {
        List<SysUserRolePO> sysUserRolePO = sysUserRoleMapper.getUserRole(uid);
        List<Map<String,String>> roleList = new ArrayList<>();
        for (SysUserRolePO s : sysUserRolePO) {
            for (SysRole sy : s.getSysRoles()) {
                String perName = sy.getName();
                List<SysRolePermission> rolePermissions = sy.getRolePermission();
                for (SysRolePermission sysRolePermission : rolePermissions) {
                    for (SysPermission sysPermission : sysRolePermission.getPermissions()) {
                        Map<String,String> roleMap = new HashMap<>();
                        String url = sysPermission.getUrl();
                        System.out.println(url);
                        roleMap.put("url",url);
                        roleMap.put("name",perName);
                        roleList.add(roleMap);
                        System.out.println("权限身份：" + perName);
                        System.out.println("权限链接：" + url);
                        System.out.println("roleList==="+roleList);
                    }
                }

//                List url = sy.getRolePermission().stream().map(item->item.getPermissions()).
//                        collect(Collectors.toList()).stream().map(sp->sp.stream().map(ss->ss.getUrl())).collect(Collectors.toList());
//                System.out.println("权限链接："+url);//输出结果是：[java.util.stream.ReferencePipeline$3@5351d373]
//                urls.stream().forEach(url-> System.out.println(url));
//                System.out.println("权限url："+urls.stream().forEach(System.out::println););

            }
        }
        List names =  roleList.stream().map(item->item.get("name")).collect(Collectors.toList());
        List urls =  roleList.stream().map(item->item.get("url")).collect(Collectors.toList());
        Map<String,List> ms = new HashMap<>();
        ms.put("name", names);
        ms.put("url", urls);
        List<Map<String,List>> ls = new ArrayList<>();
        ls.add(ms);
        System.out.println("ls==="+ls);
        return ls;
    }
}
