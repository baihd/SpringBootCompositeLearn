package com.composite.handwritingcode.concurrent.forkjoin.third;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, "the");
        DocumentTask task = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        try {
            while (!task.isDone()) {
                System.out.println("***************************************************");
                System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
                System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
                System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
                System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
                System.out.println("***************************************************");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf("Main: The word appears %d times in the document", task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
