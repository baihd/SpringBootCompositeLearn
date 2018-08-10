package com.composite.handwritingcode.concurrent.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static List<BaseHealthChecker> services;
    private static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        services = new ArrayList<>();
        latch = new CountDownLatch(2);
        services.add(new CacheHealthChecker(latch, "CacheHealthChecker"));
        services.add(new NetworkHealthChecker(latch, "NetworkHealthChecker"));
        ExecutorService executor = Executors.newFixedThreadPool(services.size());
        for (final BaseHealthChecker v : services) {
            executor.execute(v);
        }
        //等待 指导所有的线程执行完毕
        latch.await();
        for (final BaseHealthChecker v : services) {
            boolean serviceUp = v.isServiceUp();
            System.out.println(v.getServiceName() + ":" + serviceUp);
        }
    }

}
