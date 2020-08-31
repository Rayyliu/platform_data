package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.dal.mapper.platform.ExecuteMapper;
import com.platform.dal.model.platform.Execute;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @GetMapping("queryPage")
    @ApiOperation("查询所有用例记录")
    public List<Execute> queryPage(int pageNum, int pageSize, String caseName){
        return executeMapper.queryPage(pageNum,pageSize,caseName);

    }

    @GetMapping("queryAll")
    @ApiOperation("查询所有用例记录总数")
    public int queryAll(){
        return executeMapper.queryCaseTotal();
    }

    @PostMapping("update")
    @ApiOperation("更新execute表，包括编辑和每次执行用例所更新的数据")
    public int updateExecuteRecord(@RequestBody Map<String,Object> record){
        Execute execute;
        execute=JSON.parseObject(JSON.toJSONString(record),Execute.class);
        return executeMapper.updateByPrimaryKeySelective(execute);
    }

    @GetMapping("queryByCaseName")
    @ApiOperation("通过用例名称查询用例详情")
    public Execute queryByCaseName(String caseName){
       return executeMapper.selectByCaseName(caseName);
    }

    @GetMapping("queryById")
    @ApiOperation("通过用例名称查询用例详情")
    public Execute queryById(int id){
        return executeMapper.selectByPrimaryKey(id);
    }

    @GetMapping("queryCase")
    @ApiOperation("查询所有用例")
    public List<Execute> queryCase(){
       return executeMapper.queryAllCase();
    }
}
