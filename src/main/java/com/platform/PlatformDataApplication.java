package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PlatformDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformDataApplication.class, args);
    }
}

