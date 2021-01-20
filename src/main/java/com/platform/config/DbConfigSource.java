package com.platform.config;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.alibaba.nacos.NacosConfigProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class DbConfigSource {

    @Autowired
    NacosConfigProperties nacosConfigProperties;

//    @SneakyThrows
    public Map<String,String> getConfig(String projectName, String dataId, String group){

        //通过依赖注入的方式调用NacosConfigProperties实例来生成ConfigService的实例
        ConfigService configService = nacosConfigProperties.configServiceInstance();

        //获取远程配置的是NacosConfigService.getConfig(), 调用getConfigInner()。源码位置：com.alibaba.nacos.client.config.NacosConfigService.getConfig(String dataId, String group, long timeoutMs)
        String content = null;
        try {
            content = configService.getConfig("platform-data.yml", "DEFAULT_GROUP", 3000);
        } catch (NacosException e) {
            e.printStackTrace();
        }

        //把获取到的配置信息转换成YamlPropertiesFactoryBean，源码位置：org.springframework.cloud.alibaba.nacos.client.NacosPropertySourceBuilder.loadNacosData()
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ByteArrayResource(content.getBytes()));
        Properties prop = yamlFactory.getObject();

        //读取配置信息
        String url = prop.getProperty("ds." + projectName + ".url");
        String driverClassName = prop.getProperty("ds." + projectName + ".driverClassName");
        String password = prop.getProperty("ds." + projectName + ".password");
        String username = prop.getProperty("ds." + projectName + ".username");
        String tables = prop.getProperty("ds." + projectName + ".tables");
        System.out.println("driverClassName==="+driverClassName);

        //信息放入到map集合
        Map<String,String> sourceMap = new HashMap<>();
        sourceMap.put("url",url);
        sourceMap.put("driverClassName",driverClassName);
        sourceMap.put("password",password);
        sourceMap.put("username",username);
        sourceMap.put("tables",tables);
        return sourceMap;
    }

}
