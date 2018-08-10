package com.composite.handwritingcode.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker(CountDownLatch latch, String serviceName) {
        super(latch, serviceName);
    }

    @Override
    public void verifyService() {
        System.out.println("开始检查  CacheHealthChecker" + this.getServiceName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "CacheHealthChecker 检查结束 ");
    }

}
