package com.hjl.security.demo.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;


/**
 * 如果以这种方式(@Component)注册Filter，那么这个Filter会过滤所有的url
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("花费时间 : "+(new Date().getTime() - startTime));
        System.out.println("time filter end");
    }

    @Override
    public void destroy() {
        //do nothing
    }
}
