package com.juc.juc07_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/8
 * @version: 1.0
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5,2023);

        System.out.println(atomicInteger.get());
    }
}
