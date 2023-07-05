package com.juc.juc02_lock.lock06_deadLock;

import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        final Object o1 = new Object();
        final Object o2 = new Object();

        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "\t" + "自己持有o1锁，希望获取o2锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "\t" + "成功获取o2锁");
                }
            }
        },"t1").start();


        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "\t" + "自己持有o2锁，希望获取o1锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "\t" + "成功获取o1锁");
                }
            }
        },"t2").start();
    }
}
