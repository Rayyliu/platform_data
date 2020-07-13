package com.platform.controller;

import com.platform.dal.mapper.platform.ProjectMapper;
import com.platform.dal.model.platform.Project;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            log.error("插入project表失败");
            return false;
        }
    }
}
