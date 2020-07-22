package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.dal.mapper.platform.InterfaceMapper;
import com.platform.dal.model.platform.Interface;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("interface/")
public class InterFaceController {

    @Autowired
    InterfaceMapper interfaceMapper;

    @Resource
    ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping("add")
    @ApiOperation("添加新接口")
    public void addInterFace(@RequestBody Map<String,Object>  interfaceData){


        Interface interfaceDTO;
        //map转为实体类
        interfaceDTO = JSON.parseObject(JSON.toJSONString(interfaceData),Interface.class);
        for (Map.Entry<String, Object> entry:interfaceData.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if("headerdetail".equals(key)){
                interfaceDTO.setHeaderdetail(objectMapper.writeValueAsString(value));
            }
            if("body".equals(key)){
                interfaceDTO.setBody(objectMapper.writeValueAsString(value));
            }

        }
        System.out.println(interfaceDTO);
        interfaceMapper.insertSelective(interfaceDTO);
    }
    }
