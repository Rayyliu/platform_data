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
@MapperScan(basePackages = "com.platform.dal.mapper.platform", sqlSessionFactoryRef = "platformSqlSessionFactory")
public class PlatformDataSourceConfig extends DruidBaseConfig {

    @Value("${ds.platform.url}")
    private String url;

    @Value("${ds.platform.username}")
    private String username;

    @Value("${ds.platform.password}")
    private String password;

    @Value("${ds.platform.driverClassName}")
    private String driverClassName;

    @Bean(name = "platformDataSource")
    public DataSource platformDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return creatDataSource(dataSource, url, username, password, driverClassName);
    }

    @Bean(name = "platformTransactionManager")
    public DataSourceTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(platformDataSource());
    }

    @Bean(name = "platformSqlSessionFactory")
    public SqlSessionFactory platformSqlSessionFactory(@Qualifier("platformDataSource") DataSource platformDataSource)
            throws Exception {

        //自动使用驼峰命名属性映射字段,userId -> user_id
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(platformDataSource);
        sessionFactory.setConfiguration(configuration);

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/**/*.xml");
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
}