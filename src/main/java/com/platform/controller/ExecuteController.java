package com.platform.controller;


import com.alibaba.fastjson.JSON;
import com.platform.dal.mapper.platform.ExecuteMapper;
import com.platform.dal.model.platform.Execute;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("execute/")
public class ExecuteController {

    @Autowired
    ExecuteMapper executeMapper;

    @PostMapping("add")
    @ApiOperation("添加用例表执行记录")
    public int add(@RequestBody Map<String,Object> record){
        Execute execute;
        execute=JSON.parseObject(JSON.toJSONString(record),Execute.class);
        return executeMapper.insertSelective(execute);
    }
}
