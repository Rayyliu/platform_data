package com.platform.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import sun.plugin2.util.SystemUtil;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * 自定义sessionManager,每一个微服务都要有，所以可以写一个公共的sessionManager
 */
public class PlatformSessionManager  extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";


    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String sessionId=null;
        if(!StringUtils.isEmpty(id)) {
//            sessionId = id.substring(7);
            sessionId = id.replace("Bearer ","");
        }
//        id= "3ddaf3ca-1a0d-4d74-93c0-9163f2e8733e";
//        id= "c9fbe197-673c-4901-8b16-5b2e1c7f7460";
        if(!StringUtils.isEmpty(sessionId)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return sessionId;
        }else {
            return super.getSessionId(request, response);
        }
    }
}
