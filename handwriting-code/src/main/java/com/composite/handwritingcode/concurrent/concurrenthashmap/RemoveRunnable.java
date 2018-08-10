package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.Map;
import java.util.Random;

public class RemoveRunnable implements Runnable {
    Map<Integer, Integer> hashMap;

    public RemoveRunnable(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        int random = new Random().nextInt(1000);
        while (true) {
            hashMap.remove(random);
        }
    }
}
