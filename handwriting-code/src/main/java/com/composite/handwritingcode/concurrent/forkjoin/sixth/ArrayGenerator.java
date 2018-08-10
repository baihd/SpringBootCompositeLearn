package com.composite.handwritingcode.concurrent.forkjoin.sixth;

import java.util.Random;

public class ArrayGenerator {
    public int[] generator(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}
