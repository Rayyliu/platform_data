package com.platform.controller;

import com.platform.entity.ResponseResult;
import com.platform.entity.StatusCode;
import com.platform.generator.GenerateDataSourceGenerator;
import com.platform.generator.MybatisGenerator;
import com.platform.generator.TestBaseCodeGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("dataSource"))
public class dbGenController {

    @Autowired
    GenerateDataSourceGenerator generateDataSourceGenerator;

    @Autowired
    MybatisGenerator mybatisGenerator;

    @Autowired
    TestBaseCodeGenerator testBaseCodeGenerator;

    /**
     * dal/config/testbase生成器
     */
    @ApiOperation("数据库名称全小写，如supplychain,生成Xxxxtestbase会有延迟")
    @GetMapping("/gen")
    public ResponseResult dbgDen(String dbName) throws Exception{
        mybatisGenerator.dbCodeGenerator(dbName);
        generateDataSourceGenerator.generateDataSource(dbName);
        testBaseCodeGenerator.gen(dbName);
        return new ResponseResult(StatusCode.OK, true, "添加成功");
    }

}
