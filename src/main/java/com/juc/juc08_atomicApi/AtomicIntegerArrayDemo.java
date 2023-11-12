package com.juc.juc08_atomicApi;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/17
 * @version: 1.0
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray1 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }
    }
}
