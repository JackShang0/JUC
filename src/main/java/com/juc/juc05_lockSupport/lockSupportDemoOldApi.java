package com.juc.juc05_lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: shangqj
 * @date: 2023/7/6
 * @version: 1.0
 */
public class lockSupportDemoOldApi {
    public static void main(String[] args) {
        /**
         * wait 和 notify 方法的使用及注意事项：
         * 1、wait和notify方法必须要在锁内（即同步代码块或同步方法内）成对出现，否则会报错
         * 2、notify的执行顺序必须要在wait之后，无法唤醒程序，程序会一直阻塞
         */
        //waitNotify();

        /**
         * condition中的 await 和 single 方法使用
         * condition 中的线程等待和唤醒方法，需要先获取锁
         * 一定是先 await 后 single ，否则线程不会被唤醒
         */
        awaitSingle();


    }

    private static void awaitSingle() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "  come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() +" 被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                reentrantLock.unlock();
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            reentrantLock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() +" 发出唤醒通知");
            }finally {
                reentrantLock.unlock();
            }

        },"t2").start();
    }

    private static void waitNotify() {
        Object o = new Object();

        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"\t"+"  come in");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            synchronized (o){
                o.notify();
                System.out.println(Thread.currentThread().getName()+" 发出通知");
            }
        },"t2").start();
    }
}
