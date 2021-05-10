package com.platform.controller;


import com.platform.dal.mapper.platform.EnvMapper;
import com.platform.dal.model.platform.Env;
import com.platform.entity.ResponseResult;
import com.platform.entity.ResultCode;
import com.platform.entity.entity.PageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("env/")
@Transactional(rollbackFor = Exception.class)
public class EnvController {

    @Autowired
    EnvMapper envMapper;

    @PostMapping("add")
    @ApiOperation("env表新建数据")
    public int addEnv(@RequestBody Env env){
        return envMapper.insertSelective(env);
    }


//    @CrossOrigin
    @GetMapping("queryPage")
//    @ApiImplicitParams({
//    @ApiImplicitParam(name="envName",value="测试环境",paramType = "query")})
    public ResponseResult queryPage(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize")int pageSize,
                               @RequestParam(value = "envName",required = false)String envName){

//        PageEntity pageEntity = new PageEntity(page,pageSize,projectMapper.query(page,pageSize),projectMapper.queryProjectTotal());

        return new ResponseResult().success(ResultCode.SUCCESS.getCode(),true,"查询测试环境列表成功",envMapper.queryPage(pageNum,pageSize,envName));
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

    @GetMapping("queryEnvDetail")
    @ApiOperation("查询环境详情")
    public List<Env> queryEnvDetail(){
        return envMapper.queryEnv();
    }

    @GetMapping("getPathByEnvName")
    @ApiOperation("查询环境详情")
    public String getPathByEnvName(String envName){
        return envMapper.queryPathByEnvName(envName);
    }

    @GetMapping("queryAllEnv")
    @ApiOperation("查询环境详情")
    public ResponseResult queryAllEnv(){
        return new ResponseResult(ResultCode.SUCCESS.getCode(),true,"查询环境成功",envMapper.queryEnv().stream().map(item->item.getEnvName()));
    }

}
