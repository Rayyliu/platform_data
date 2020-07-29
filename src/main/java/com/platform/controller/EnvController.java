package com.platform.controller;


import com.platform.dal.mapper.platform.EnvMapper;
import com.platform.dal.model.platform.Env;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("env/")
public class EnvController {

    @Autowired
    EnvMapper envMapper;

    @PostMapping("add")
    @ApiOperation("env表新建数据")
    public int addEnv(@RequestBody Env env){
        return envMapper.insertSelective(env);
    }

    @GetMapping("queryPage")
//    @ApiImplicitParams({
//    @ApiImplicitParam(name="envName",value="测试环境",paramType = "query")})
    public List<Env> queryPage(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize")int pageSize,
                               @RequestParam(value = "envName",required = false)String envName){
        return envMapper.queryPage(pageNum,pageSize,envName);
    }

    @GetMapping(value = "queryTotal")
    public int queryTotal(){
        return envMapper.queryEnvTotal();
    }

    @GetMapping("singleDelet")
    @ApiOperation("单挑删除")
    public int singleDelet(@RequestParam("envId") String envId){
       return envMapper.deleteByPrimaryKey(Integer.valueOf(envId));
    }

    @GetMapping("deletes")
    @ApiOperation("批量删除环境")
    public void deletes(@RequestParam("ids") int[] ids ){
        envMapper.deletes(ids);
    }

    @PostMapping("edit")
    @ApiOperation("修改环境配置env表")
    public int edit(@RequestBody Env env){
       return envMapper.updateByPrimaryKeySelective(env);

    }
}
