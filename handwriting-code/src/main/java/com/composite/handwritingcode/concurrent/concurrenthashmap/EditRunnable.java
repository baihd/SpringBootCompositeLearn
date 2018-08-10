package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.Map;

public class EditRunnable implements Runnable {
    Map<Integer, Integer> hashMap;

    public EditRunnable(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        hashMap.put(0, hashMap.get(0) + 1);
    }
}
