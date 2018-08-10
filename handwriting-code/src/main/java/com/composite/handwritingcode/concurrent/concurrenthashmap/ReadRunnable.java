package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.Map;

public class ReadRunnable implements Runnable {
    Map<Integer, Integer> hashMap;

    public ReadRunnable(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        System.out.println("value" + hashMap.get(0));
    }
}
