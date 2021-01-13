package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PlatformDataApplication extends SpringBootServletInitializer {

    //swagger地址：http://localhost:8081/swagger-ui.html#!/
    public static void main(String[] args) {
        SpringApplication.run(PlatformDataApplication.class, args);
    }
}

