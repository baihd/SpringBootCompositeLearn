package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.Map;
import java.util.Random;

public class AddRunnable implements Runnable {
    Map<Integer, Integer> hashMap;

    public AddRunnable(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        while (true) {
            int random = new Random().nextInt();
            hashMap.put(random, random);
        }
    }
}
