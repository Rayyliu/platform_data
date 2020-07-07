package com.platform.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class GenerateDataSourceGenerator {

    public  void generateDataSource(String dbName) throws IOException, TemplateException {
//        String className = "NotificationDataSourceConfig";
        String className = new TestBaseCodeGenerator().toUpperCaseFirstOne(dbName)+"DataSourceConfig";
        Configuration cfg = getCfg();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("dbName",dbName);
        root.put("className",className);
        String outFilePath = Thread.currentThread().getContextClassLoader().getResource("com/platform/config/DruidBaseConfig.class").getFile().
                replace("target/classes/com/platform/config/DruidBaseConfig.class","src/main/java/com/platform/config/"+className+".java");
        if(!new File("outFilePath").exists()){
            Template tp = cfg.getTemplate("generateDataSourceConfig.ftl");
            Writer outFilePathWrite = new PrintWriter(new BufferedWriter(new FileWriter(outFilePath)));
            tp.process(root,outFilePathWrite);
            System.out.println("模板创建成功");
        }

    }

    public Configuration getCfg() throws IOException {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getFile().
                replace("target/classes","src/main/resources/ftl");
        //Configuration.VERSION_2_3_26存在于最高版本为freemarker-2.3.28.jar包内，更高版本的不存在
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setDirectoryForTemplateLoading(new File(filePath));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

}
