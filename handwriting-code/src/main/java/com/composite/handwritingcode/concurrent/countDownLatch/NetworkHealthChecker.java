package com.composite.handwritingcode.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker {
    public NetworkHealthChecker(CountDownLatch latch, String serviceName) {
        super(latch, serviceName);
    }

    @Override
    public void verifyService() {
        System.out.println("开始检查 " + this.getServiceName());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " 检查结束");
    }
}
