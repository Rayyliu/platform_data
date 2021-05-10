package com.platform.exception;


import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice(basePackages = "com.platform.controller")
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(SpelEvaluationException.class)
    public ModelAndView errorResult(Exception e) {
        Map<String, Object> errorResultMap = new HashMap<String, Object>(8);
        errorResultMap.put("errorCode", "500");
        errorResultMap.put("errorMessage", "系统错误");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errors", errorResultMap);
        logger.error(e.getLocalizedMessage());
        return modelAndView;
    }

    /**
     * 捕获权限不足异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        return "对不起，你没有访问权限！";
    }

}
