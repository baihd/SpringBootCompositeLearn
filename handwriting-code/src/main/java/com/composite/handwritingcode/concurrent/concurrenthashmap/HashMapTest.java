package com.composite.handwritingcode.concurrent.concurrenthashmap;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {

        /*HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 0);
        for (int i = 0; i < 100; i++) {
            new Thread(new EditRunnable(hashMap)).start();
        }
        new Thread(new ReadRunnable(hashMap)).start();
        System.out.println("main value is" + hashMap.get(0));*/


        HashMap<Integer, Integer> hashMap2 = new HashMap();
        for (int i = 0; i < 1000; i++) {
            hashMap2.put(i, i);
        }
        new Thread(new AddRunnable(hashMap2)).start();
        new Thread(new RemoveRunnable(hashMap2)).start();
        new Thread(new IteratorRunnable(hashMap2)).start();

    }
}
