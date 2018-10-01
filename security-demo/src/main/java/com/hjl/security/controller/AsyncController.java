package com.hjl.security.controller;

import com.hjl.security.async.DeferredResultHolder;
import com.hjl.security.async.MockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/order")
    public Callable<String> order(){
        logger.info("主线程开始");

        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(1000);
            logger.info("副线程返回");
            return "success";
        };

        logger.info("主线程结束");

        return result;
    }

    @RequestMapping("commit")
    public DeferredResult<String> commit() throws InterruptedException {
        logger.info("主线程开始");
        String orderNumber = "test001";

        mockQueue.order(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber , result);


        logger.info("主线程结束");

        return result;
    }
}
