package com.hjl.security.demo.controller.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody Map<String , Object> handleRuntimeException(RuntimeException ex){
        Map<String , Object> map = new HashMap<>();
        map.put("code" , "0002");
        map.put("message" , ex.getMessage());
        return map;
    }
}
