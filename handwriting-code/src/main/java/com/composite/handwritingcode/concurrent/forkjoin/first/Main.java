package com.composite.handwritingcode.concurrent.forkjoin.first;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        Task task = new Task(products, 0, products.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        try {
            while (!task.isDone()) {
                System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
                System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
                System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭线程池
        pool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.println("Main: The process has completed normally.");
        }
        //确认是否所有的价格都已经改变
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12)
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
        }
        System.out.println("Main: End of the program.");


    }
}
