package com.platform.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {


    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    private String password;

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager  securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");//针对未登录的客户认证失败后的跳转地址
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");//针对认证通过但权限不足的跳转地址
        //构建拦截器
        Map<String,String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();

        //放行swagger相关配置
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/shiro/ignore/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
//        filterChainDefinitionMap.put("/user/**", "roles[普通用户]");
        filterChainDefinitionMap.put("/user/**", "perms[userInfo:add]");
//        filterChainDefinitionMap.put("/project/**", "perms[userInfo:view]");
        filterChainDefinitionMap.put("/project/**", "anon");
//        filterChainDefinitionMap.put("/plan/**", "roles[admin]");

//        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/**","authc");//注意：该行配置必须放置在上述swagger配置代码之后，不然不会生效：

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }







    // 配置url过滤器
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//
//        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
//        chainDefinition.addPathDefinition("/webjars/**", "anon");
//        chainDefinition.addPathDefinition("/v2/**", "anon");
//        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");
//        chainDefinition.addPathDefinition("/shiro/ignore/**", "anon");
//        // all other paths require a logged in user
//        chainDefinition.addPathDefinition("/login","anon");
//        chainDefinition.addPathDefinition("/**", "authc");
//        return chainDefinition;
//    }


    /***
     * 密码加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    /***
     * 自定义realm
     * @return
     */
    @Bean
    public PlatformShiroRealm myShiroRealm() {
        PlatformShiroRealm myShiroRealm = new PlatformShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        PlatformRolePermissionResolver resolver = new PlatformRolePermissionResolver();
        myShiroRealm.setRolePermissionResolver(resolver);
        myShiroRealm.setAuthenticationCacheName("platformAuthenticationCache");
        myShiroRealm.setAuthorizationCacheName("platformAuthorizationCache");
        myShiroRealm.setAuthorizationCachingEnabled(true);
        myShiroRealm.setAuthenticationCachingEnabled(true);
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSessionManager getWebSessionManager(){

        PlatformSessionManager defaultWebSessionManager = new PlatformSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());

//        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        defaultWebSessionManager.setGlobalSessionTimeout(120000L); //设置全局会话超时时间，默认1800000（30分钟），即如果30分钟内没有访问会话将过期 10000
//        defaultWebSessionManager.setSessionValidationSchedulerEnabled(false);//是否开启会话验证器，默认是开启的,默认true
//        defaultWebSessionManager.setDeleteInvalidSessions(false);//是否删除失效的session，默认为true，所以存在redis里失效的session都会在程序执行完毕后自动删除
//        defaultWebSessionManager.setCacheManager(memoryConstrainedCacheManager());
//        defaultWebSessionManager.setSessionDAO(redisSessionDAO());


        return defaultWebSessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis, 使用的是shiro-redis开源插件
     *
     * @return RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        //session在redis中的保存时间,最好大于session会话超时时间
        redisSessionDAO.setExpire(1800);//redis设置有效期为30分钟，与session的默认有效时间一致
        return redisSessionDAO;
    }


    /**
     * Session ID 生成器
     *
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(getWebSessionManager());

        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
        return new MemoryConstrainedCacheManager();
    }



    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * 加入注解的使用，不加入这个注解不生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager  securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }


    /**
     * 配置缓存管理器
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());

        //设置主键名称，shiro-redis插件用这个缓存用户信息
        redisCacheManager.setPrincipalIdFieldName("Username");
        return redisCacheManager;
    }


    /**
     * 配置缓存服务器信息
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("172.31.1.19");
        redisManager.setHost(redisHost);
//        redisManager.setPort(6379);
        redisManager.setPort(Integer.valueOf(redisPort));
        //19测试环境redis没密码
//        redisManager.setPassword("");
        return redisManager;
    }


    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError"); // 数据库异常处理
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }
}
