package com.platform.generator;

import com.platform.config.MybatisConfig;
import com.platform.entity.Param;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import io.micrometer.core.instrument.config.InvalidConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.internal.NullProgressCallback;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TestBaseCodeGenerator extends MybatisConfig {

    public void gen(String dbName) throws IOException, TemplateException, SQLException, InterruptedException, InvalidConfigurationException {
        String packageName = "com.platform.testbase";
        String author = "RayLiu";
        testBaseCodeGenerate(dbName,packageName,author);
    }

    public void testBaseCodeGenerate(String dbName,String packageName,String author) throws IOException, TemplateException, InterruptedException, SQLException, InvalidConfigurationException {

        List<Map<String,Object>> tableList = new ArrayList<>();
        Context context = new Context(ModelType.FLAT);
        mybatisConfig(context,dbName);
        ProgressCallback callback=new NullProgressCallback();
        List<String> warnings = new ArrayList<>();
        Set<String> fullyQualifiedTableNames = null;
        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        List<GeneratedXmlFile> generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
        context.introspectTables(callback,warnings,fullyQualifiedTableNames);
        context.generateFiles(callback, generatedJavaFiles,generatedXmlFiles, warnings);
        for(GeneratedJavaFile gjf : generatedJavaFiles){
            String name = gjf.getCompilationUnit().getType().getShortNameWithoutTypeArguments();
            if(!StringUtils.endsWithAny(name,"Example","Mapper")){
                Map<String,Object> tableMap = new HashMap<>();
                List<Param> pars = new ArrayList<>();
                tableMap.put("tableName",name);
                tableMap.put("table",toLowerCaseFirstOne(name));
                TopLevelClass tlc = (TopLevelClass) gjf.getCompilationUnit();
                for(Field f : tlc.getFields()){
                    if ("serialVersionUID".equals(f.getName())) {
                        continue;
                    }
                    Param par = new Param();
                    par.setName(f.getName());
                    par.setTypeName(f.getType().getShortNameWithoutTypeArguments());
                    if(par.getTypeName().equals("Long")){
                        par.setTypeName("long");
                    }
                    pars.add(par);
                }
                tableMap.put("pars",pars);
                tableList.add(tableMap);
            }
            continue;
        }
        Configuration cfg = getCfg();
//            YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//            yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yml"));
//            Properties properties = yamlPropertiesFactoryBean.getObject();
//            String table = properties.getProperty("ds."+dbName+".tables");
//            List<String> tableList = new ArrayList<>();
//            String[] tables = table.split(",");
//            for(int i = 0;i<tables.length;i++) {
//                String tab = tables[i];
//                tableList.add(tab);
//            }
        String className = toUpperCaseFirstOne(dbName)+"TestBase";
//            String outFile = Thread.currentThread().getContextClassLoader().getResource("com/testbase/ScTestBase.class").getFile().
//                    replace("target/classes","src/main/java").replace("ScTestBase.class",className+".java");
        String testBaseDir = "src/main/java/com/platform/testbase/";
        File file = new File(testBaseDir);
        if(file.exists()){
            file.delete();
        }
        file.mkdir();
        String outFile = testBaseDir+className+".java";
        Map<String,Object> root = new HashMap();
        root.put("packageName",packageName);
        root.put("dbName",dbName);
        root.put("className",className);
        root.put("tableList",tableList);
        root.put("time",new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        root.put("author",author);
        if(!new File(outFile).exists()){
            Template template =cfg.getTemplate("generateTestBase.ftl");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
            template.process(root,out);
            System.out.println("更新成功" + outFile);
        }
    }

    public static Configuration getCfg() throws IOException {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getFile().
                replace("target/classes","src/main/resources/ftl");
        //Configuration.VERSION_2_3_26存在于最高版本为freemarker-2.3.28.jar包内，更高版本的不存在
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setDirectoryForTemplateLoading(new File(filePath));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
