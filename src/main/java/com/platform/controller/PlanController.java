package com.platform.controller;

import com.platform.dal.mapper.platform.PlanMapper;
import com.platform.dal.model.platform.Execute;
import com.platform.dal.model.platform.Interface;
import com.platform.dal.model.platform.Plan;
import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan/")
@Transactional(rollbackFor = Exception.class)
public class PlanController {

    @Autowired
    PlanMapper planMapper;

    @PostMapping("add")
    @ApiOperation("新增测试计划")
    public ResponseResult add(@RequestBody Plan plan){
        return new ResponseResult(ResultCode.SUCCESS.getCode(),true,"", planMapper.insert(plan));
    }

    @GetMapping("queryPage")
    @ApiOperation("分页查询")
    public ResponseResult queryPage(@RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize")int pageSize,
                                     @RequestParam(value = "planName",required = false)String planName){
        return new ResponseResult(ResultCode.SUCCESS.getCode(),true,"分页查询成功",planMapper.queryPage(pageNum,pageSize,planName));
    }
}
