package com.platform.controller;

import com.platform.dal.mapper.platform.ProjectMapper;
import com.platform.dal.model.platform.Project;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("project/")
public class ProjectController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    ProjectMapper projectMapper;

    @PostMapping("add")
    @ApiOperation("向project表插数据")
    public boolean addProject(@RequestBody Project project){
        try{
            projectMapper.insert(project);
            return true;
        }catch (Exception e){
            log.error("插入project表失败",e);
            return false;
        }
    }

    @GetMapping("query")
    @ApiOperation("项目列表查询")
    public List<Project> query(int page, int pageSize){
        return projectMapper.query(page,pageSize);
    }

    @GetMapping("queryTotal")
    @ApiOperation("查询项目总数")
    public int queryProjectTotal(){
        return projectMapper.queryProjectTotal();
    }

    @PostMapping("updateProject")
    @ApiOperation("根据projectId修改project表数据")
    public int updateProject(@RequestBody Project project){
        return projectMapper.updateByPrimaryKey(project);
    }

    @GetMapping("queryById")
    @ApiOperation("通过id查询项目详情")
    public Project query(int projectId){
        return projectMapper.selectByPrimaryKey(projectId);
    }

    @GetMapping("delById")
    @ApiOperation("单挑删除项目")
    public void del(String projectId){
        projectMapper.deleteByPrimaryKey(new Integer(projectId));
    }

    @GetMapping("updateValid")
    @ApiOperation("修改project表valid值")
    public void updateValid(int projectId,boolean valid){
        projectMapper.updateValid(projectId,valid);
    }

    @GetMapping("deletes")
    @ApiOperation("通过projectId批量删除project表数据")
    public void deletes(String[] projectIds){
        projectMapper.deletes(projectIds);
    }
}

