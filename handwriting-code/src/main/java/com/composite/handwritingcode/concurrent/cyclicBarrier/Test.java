package com.composite.handwritingcode.concurrent.cyclicBarrier;

public class Test {
    public static void main(String[] args) {
        CyclicBarrierRunnable cyclicBarrierRunnable = new CyclicBarrierRunnable();
        cyclicBarrierRunnable.count();
    }
}
