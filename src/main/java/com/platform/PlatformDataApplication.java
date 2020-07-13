package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PlatformDataApplication {

    //swagger地址：http://localhost:8081/swagger-ui.html#!/
    public static void main(String[] args) {
        SpringApplication.run(PlatformDataApplication.class, args);
    }
}

