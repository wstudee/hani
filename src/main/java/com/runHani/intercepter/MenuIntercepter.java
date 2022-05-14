package com.runHani.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class MenuIntercepter implements HandlerInterceptor{
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        logger.info("[HANI] preHandle");
        
        
        
        
        
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
    	String task = request.getRequestURI().split("/")[1];
    	modelAndView.addObject("task", task);
        logger.info("[HANI] postHandle");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception ex
    ) throws Exception {
        logger.info("[HANI] afterCompletion");
    }

}
