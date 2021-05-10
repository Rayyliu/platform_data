package com.platform.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



/**
 * 使用CORS，用于解决ajax跨域访问问题
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1.添加CORS配置信息
        //1) 允许的域,不要写*，否则cookie就无法使用;允许的网站域名，全允许则设为 *
        corsConfiguration.addAllowedOrigin("*");

        //2) 是否发送Cookie信息
        corsConfiguration.setAllowCredentials(true);

        //3) 允许的请求方式, * 为全部
        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedMethod("HEAD");
//        corsConfiguration.addAllowedMethod("GET");
//        corsConfiguration.addAllowedMethod("PUT");
//        corsConfiguration.addAllowedMethod("POST");
//        corsConfiguration.addAllowedMethod("DELETE");
//        corsConfiguration.addAllowedMethod("PATCH");
        corsConfiguration.setMaxAge(3600L);

        // 4）允许的头信息, * 为全部
        corsConfiguration.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求,
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfiguration);

        //3.返回新的CorsFilter.
        //return new CorsFilter(configSource);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
        // 这个顺序很重要，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;

    }
}
