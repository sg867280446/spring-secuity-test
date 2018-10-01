package com.hjl.security.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",new Date().getTime());
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        /**
         *  必须要返回true，postHandle与afterCompletion才会执行。
         */
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        /**
         * 如果有Exception，这个方法不会被执行
         */
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("postHandle消耗时间 : "+(new Date().getTime() - startTime));
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        /**
         * 无论有无Exeception,这个方法都会执行。
         */
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("afterCompletion消耗时间 : "+(new Date().getTime() - startTime));

        /**
         * 因为有ControllerExceptionHandler的缘故，所以的RuntimeException都会被它处理，所以下面的ex会是空
         * 除了RuntimeException，其他Exception都会被打印出来。
         */
        System.out.println("ex : "+ex);

    }
}
