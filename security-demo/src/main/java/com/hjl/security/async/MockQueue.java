package com.hjl.security.async;

import org.springframework.stereotype.Component;

@Component
public class MockQueue {
    private String returnOrderNumber;

    public void order(String orderNumber) {
        new Thread(()->{
            System.out.println("接到下单请求");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.returnOrderNumber = orderNumber;
            System.out.println("下单请求处理完毕");
        }).start();

    }

    public String getReturnOrderNumber() {
        return returnOrderNumber;
    }


    public void setReturnOrderNumber(String returnOrderNumber) {
        this.returnOrderNumber = returnOrderNumber;
    }
}
