package com.juc.juc08_atomicApi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description: 以线程安全的方式操作非线程安全对象的某些字段
 * @author: shangqj
 * @date: 2023/8/21
 * @version: 1.0
 */
public class AtomicIntegerFieldUpdaterDemo {
    /**
     * 需求：10个线程，每个线程转账1000，不适用synchronized，尝试使用AtomicIntegerFieldUpdater来实现
     */
    public static void main(String[] args) throws InterruptedException {
        BankCount bankCount = new BankCount();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (Integer integer = 1; integer <= 10; integer++) {
            new Thread(() -> {
                try {
                    for (int i = 1; i <= 1000; i++) {
                        //bankCount.add();
                        bankCount.transMoney(bankCount);
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

        System.out.println(bankCount.money);
    }
}


class BankCount {
    String bankId = "1";
    public volatile int money = 0;

    public void add() {
        money++;
    }

    AtomicIntegerFieldUpdater<BankCount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankCount.class,"money");

    //不加synchronized，保证局部高新能原子性
    public void transMoney(BankCount bankCount){
        fieldUpdater.getAndIncrement(bankCount);
    }

    //加锁可以实现，此处不使用synchronized，使用volatile+AtomicIntegerFieldUpdate细粒度处理
   /* public synchronized void add() {
        money++;
    }*/
}