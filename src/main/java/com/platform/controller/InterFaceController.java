package com.platform.controller;

import com.platform.dal.mapper.platform.InterfaceMapper;
import com.platform.dal.model.platform.Interface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("interface/")
public class InterFaceController {

    @Autowired
    InterfaceMapper interfaceMapper;

    @PostMapping("add")
    @ApiOperation("添加新接口")
    public void addInterFace(@RequestBody Interface inter){
        interfaceMapper.insertSelective(inter);
    }
    }
