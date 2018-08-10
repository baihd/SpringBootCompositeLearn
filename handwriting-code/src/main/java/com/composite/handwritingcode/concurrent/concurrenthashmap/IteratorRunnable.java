package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.Map;

public class IteratorRunnable implements Runnable{
    Map<Integer, Integer> hashMap;

    public IteratorRunnable(Map<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void run() {
        System.out.println("------------------ " + hashMap.size());
        for (Integer value : hashMap.values()) {
//            System.out.println("value " + value);
        }
        System.out.println("+++++++++++++++++++ " + hashMap.size());
    }

}
