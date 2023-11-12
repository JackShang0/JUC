package com.juc.juc07_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/15
 * @version: 1.0
 */
public class SpinLockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock(){
        System.out.println(Thread.currentThread().getName() + "come in ...");

        while (!threadAtomicReference.compareAndSet(null,Thread.currentThread())){

        }
    }

    public void unLock(){
        System.out.println(Thread.currentThread().getName() + "come out ...");

        threadAtomicReference.compareAndSet(Thread.currentThread(),null);
    }

    /**
     * 题目：实现一个自旋锁，复习cas思想
     * 自旋锁的好处： 循环比较获取没有类似wait的阻塞
     *
     * 通过cas操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒种，
     *  B随后进来后发现当前有线程持有锁，所以只能通过自旋锁等待，直到A释放锁后B随后抢到
     * @param args args
     */
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unLock();
        },"A").start();


        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            spinLockDemo.lock();

            spinLockDemo.unLock();
        },"B").start();


    }
}
