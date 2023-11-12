package com.juc.juc08_atomicApi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/16
 * @version: 1.0
 */
public class AtomicIntegerDemo {

    public static final Integer size = 50;

    /**
     * 此处问题说明： main线程获取执行结果，可能获取的结果是执行过程中的值，线程的add操作未完全执行完，main线程获取到不是最终结果
     * 处理办法：sleep->countDownLatch
     * 1、让线程睡一会（可以满足要求，但是睡眠时间不好把控）
     * 2、使用CountDownLatch来解决
     *
     * @param args
     */
    public static void main(String[] args) {
        countDownLatchQuestion();
        countDownLatch();
    }

    private static void countDownLatchQuestion() {
        Number number = new Number();
        for (Integer integer = 1; integer <= size; integer++) {
            new Thread(() -> {
                for (int i = 1; i <= 1000; i++) {
                    number.addPlusPlus();
                }
            }, String.valueOf(integer)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "    result   " + number.atomicInteger.get());
    }


    private static void countDownLatch() {
        Number number = new Number();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (Integer integer = 1; integer <= size; integer++) {
            new Thread(() -> {
                try {
                    for (int i = 1; i <= 1000; i++) {
                        number.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(integer)).start();

        }
        try {
            //等待countDownLatch执行结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "    result   " + number.atomicInteger.get());
    }
}


class Number {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}