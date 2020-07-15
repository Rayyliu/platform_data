package com.platform.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@Configuration
@MapperScan(basePackages = "com.platform.dal.mapper.${dbName}", sqlSessionFactoryRef = "${dbName}SqlSessionFactory")
public class ${className} extends DruidBaseConfig {

    @Value("${r"${"}ds.${dbName}.url}")
    private String url;

    @Value("${r"${"}ds.${dbName}.username}")
    private String username;

    @Value("${r"${"}ds.${dbName}.password}")
    private String password;

    @Value("${r"${"}ds.${dbName}.driverClassName}")
    private String driverClassName;

    @Bean(name = "${dbName}DataSource")
    public DataSource ${dbName}DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return creatDataSource(dataSource, url, username, password, driverClassName);
    }

    @Bean(name = "${dbName}TransactionManager")
    public DataSourceTransactionManager ${dbName}TransactionManager() {
        return new DataSourceTransactionManager(${dbName}DataSource());
    }

    @Bean(name = "${dbName}SqlSessionFactory")
    public SqlSessionFactory ${dbName}SqlSessionFactory(@Qualifier("${dbName}DataSource") DataSource ${dbName}DataSource)
            throws Exception {

        //自动使用驼峰命名属性映射字段,userId -> user_id
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(${dbName}DataSource);
        sessionFactory.setConfiguration(configuration);

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*.xml");
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
}