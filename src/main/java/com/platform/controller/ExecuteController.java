package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.dal.mapper.platform.ExecuteMapper;
import com.platform.dal.model.platform.Execute;
import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "execute/")
@Transactional(rollbackFor = Exception.class)
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


    @GetMapping(value = "queryPage")
    @ApiOperation("查询所有用例记录")
    public  List<Execute> queryPage(int pageNum, int pageSize, String caseName){
        List<Execute> Executes =executeMapper.queryPage(pageNum,pageSize,caseName);
//        Executes.stream().forEach(item-> System.out.println("Response==="+JSONObject.parseObject(item.getResponse()).getString("message")));
        return Executes;

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

    @GetMapping("queryByProject")
    @ApiOperation("通过项目名称查询所有用例详情")
    public ResponseResult queryByProject(String projectName){
        System.out.println("projectName==="+projectName);
        return new ResponseResult().success(ResultCode.SUCCESS.getCode(),true,"查询用例成功",executeMapper.selectByProject(projectName).stream().map(item->item.getCaseName()));
    }

    @GetMapping("queryById")
    @ApiOperation("通过用例编号查询用例详情")
    public Execute queryById(int id){
        return executeMapper.selectByPrimaryKey(id);
    }

    @GetMapping("queryCase")
    @ApiOperation("查询所有用例")
    public ResponseResult queryCase(){
       return new ResponseResult().success(ResultCode.SUCCESS.getCode(), true, "用例查询成功", executeMapper.queryAllCase());
    }

    @GetMapping("delSingleById")
    @ApiOperation("删除测试用例")
    public ResponseResult delSingleCase(String recordId){
        return new ResponseResult().success(ResultCode.SUCCESS.getCode(), true, "用例查询成功", executeMapper.deleteByPrimaryKey(new Integer(recordId)));
    }

    @GetMapping("deletes")
    @ApiOperation("通过executeRecordId批量删除execute表数据")
    public void deletes(String[] recordIds){
        executeMapper.deletes(recordIds);
    }
}
