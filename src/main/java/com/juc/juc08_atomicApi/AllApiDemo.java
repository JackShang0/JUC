package com.juc.juc08_atomicApi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description: 针对点赞这个场景 50 个线程，每个线程点赞100w次，看哪个线程的吞吐量更大
 * @author: shangqj
 * @date: 2023/8/23
 * @version: 1.0
 */
public class AllApiDemo {

    public static final int count = 10000;
    public static final int threadNumber = 50;

    /**
     * synchronized   atomicLong   LongAdder  Accumulator
     * @param args a
     * @throws InterruptedException e
     * 花费时间 1313 毫秒   clickBySynchronized  50000000
     * 花费时间 788 毫秒 	  clickByAtomicLong  50000000
     * 花费时间 122 毫秒 	  clickByLongAdder  50000000
     * 花费时间 94 毫秒 	  clickByAccumulator  50000000
     */
    public static void main(String[] args) throws InterruptedException {

        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);

        synchronizedClick(clickNumber, countDownLatch1);
        atomicLongClick(clickNumber, countDownLatch2);
        longAdderClick(clickNumber, countDownLatch3);
        longAccumulatorClick(clickNumber, countDownLatch4);
    }

    private static void synchronizedClick(ClickNumber clickNumber, CountDownLatch countDownLatch1) throws InterruptedException {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * count; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费时间 " + (endTime - startTime) + " 毫秒 \t  clickBySynchronized  " + clickNumber.number);
    }



    private static void atomicLongClick(ClickNumber clickNumber, CountDownLatch countDownLatch2) throws InterruptedException {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * count; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费时间 " + (endTime - startTime) + " 毫秒 \t  clickByAtomicLong  " + clickNumber.atomicLong);
    }

    private static void longAdderClick(ClickNumber clickNumber, CountDownLatch countDownLatch3) throws InterruptedException {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * count; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费时间 " + (endTime - startTime) + " 毫秒 \t  clickByLongAdder  " + clickNumber.longAdder.sum());
    }

    private static void longAccumulatorClick(ClickNumber clickNumber, CountDownLatch countDownLatch4) throws InterruptedException {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();

        for (int i = 1; i <= threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * count; j++) {
                        clickNumber.clickByAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();

        System.out.println("花费时间 " + (endTime - startTime) + " 毫秒 \t  clickByAccumulator  " + clickNumber.accumulator);
    }
}

class ClickNumber {
    int number = 0;

    public synchronized void clickBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void clickByAccumulator() {
        accumulator.accumulate(1);
    }
}
