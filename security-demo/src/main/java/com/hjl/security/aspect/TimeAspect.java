package com.hjl.security.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    /**
     *  抛出异常时，处理的优先顺序：
     *  1、Controller
     *  2、Aspect
     *  3、ControllerAdvice
     *  4、Interceptor
     *  5、Filter
     */



    @Around("execution(* com.hjl.security.controller.UserController.*(..))")
    public Object handleUserController(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");

        //参数列表
        for(Object arg : pjp.getArgs()){
            System.out.println(arg);
        }
        long startTime = new Date().getTime();
        Object object = pjp.proceed();
        System.out.println("耗时 : "+(new Date().getTime() - startTime));
        System.out.println("time aspect end");
        return object;
    }
}
