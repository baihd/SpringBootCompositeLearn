package com.composite.handwritingcode.concurrent.forkjoin.fifth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = new int[100];
        Task task = new Task(array, 0, 100);
        pool.execute(task);
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (task.isCompletedAbnormally()) {
            System.out.println("Main: An exception has ocurred");
            System.out.println("Main: " + task.getException());
        }
        System.out.println("Main: Result: " + task.join());
    }
}
