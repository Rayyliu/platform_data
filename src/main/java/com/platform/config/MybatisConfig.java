package com.platform.config;

import com.platform.generator.MybatisGenerator;
import org.mybatis.generator.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MybatisConfig {

    @Autowired
    DbConfigSource dbConfigSource;
    public void mybatisConfig(Context context, String dbName){

        //配置数据连接信息
        JDBCConnectionConfiguration JbConn = new JDBCConnectionConfiguration();
        JbConn.setConnectionURL(dbConfigSource.getConfig(dbName,null,null).get("url"));
        JbConn.setDriverClass(dbConfigSource.getConfig(dbName,null,null).get("driverClassName"));
        JbConn.setPassword(dbConfigSource.getConfig(dbName,null,null).get("password"));
        JbConn.setUserId(dbConfigSource.getConfig(dbName,null,null).get("username"));
        //生成实体类，存放model模型位置
        JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
        String tarProject = MybatisGenerator.class.getClassLoader().getResource("").getFile().
                replace("target/classes", "src/main/java");
        modelConfig.setTargetPackage("com.data.dal.model." + dbName);
        modelConfig.setTargetProject(tarProject);
        modelConfig.addProperty("enableSubPackages", "true");
        modelConfig.addProperty("trimStrings", "true");
        //存放mapper的xml配置文件路径
        SqlMapGeneratorConfiguration sqlMapperConfig = new SqlMapGeneratorConfiguration();
        String mapperXmlTarProject = MybatisGenerator.class.getClassLoader().getResource("").getFile().
                replace("target/classes", "src/main/resources");
        sqlMapperConfig.setTargetPackage("mybatis.mapper." + dbName);
        sqlMapperConfig.setTargetProject(mapperXmlTarProject);
        sqlMapperConfig.addProperty("enableSubPackages", "true");
        //存放mapper文件路径
        JavaClientGeneratorConfiguration clientGneneratorConfig = new JavaClientGeneratorConfiguration();
        clientGneneratorConfig.setTargetPackage("com.data.dal.mapper." + dbName);
        clientGneneratorConfig.setTargetProject(tarProject);
        clientGneneratorConfig.setConfigurationType("XMLMAPPER");
        clientGneneratorConfig.addProperty("enableSubPackages", "true");

        String tables = dbConfigSource.getConfig(dbName,null,null).get("tables");
        GeneratedKey generatedKey = new GeneratedKey("id", "MYSQL", true, "");
        if (!StringUtils.isEmpty(tables)) {
            String[] table = tables.split(",");
            Set<String> tab = new HashSet<>(Arrays.asList(table));
            for (String st : tab) {
                TableConfiguration tableConfiguration = new TableConfiguration(context);
                tableConfiguration.setTableName(st.trim());
                tableConfiguration.setGeneratedKey(generatedKey);
                tableConfiguration.setInsertStatementEnabled(true);
                context.addTableConfiguration(tableConfiguration);
            }

        }
        PluginConfiguration pc1 = new PluginConfiguration();
        pc1.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        PluginConfiguration pc2 = new PluginConfiguration();
        pc2.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");

        context.setJavaClientGeneratorConfiguration(clientGneneratorConfig);
        context.setJavaModelGeneratorConfiguration(modelConfig);
        context.setJdbcConnectionConfiguration(JbConn);
        context.setSqlMapGeneratorConfiguration(sqlMapperConfig);
        context.addPluginConfiguration(pc1);
        context.addPluginConfiguration(pc2);
        context.setId(dbName);

        //生成的Java文件的编码
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");
        context.addProperty("javaFileEncoding", "UTF-8");
        context.setTargetRuntime("MyBatis3");
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        //是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.）
        javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
    }

}
