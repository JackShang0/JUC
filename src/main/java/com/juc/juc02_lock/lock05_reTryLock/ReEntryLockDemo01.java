package com.juc.juc02_lock.lock05_reTryLock;


/**
 * @description   Synchronized 可重入锁的相关案例
 * @author: shangqj
 * @date: 2023/6/19
 * @version: 1.0
 */
public class ReEntryLockDemo01 {

    public static void main(String[] args) {
        //reEntryLockWay01();
        ReEntryLockDemo01 reEntryLockDemo = new ReEntryLockDemo01();
        new Thread(()->{
            reEntryLockDemo.m1();
        },"tt").start();
    }


    /**
     * 同步代码块
     */
    public static void reEntryLockWay01(){
        final Object o = new Object();

        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName() + "   外层调用...");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "   中层调用...");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName() + "   内层调用...");
                    }
                }
            }
        },"t1").start();
    }

    /***
     * 同步方法
     */
    public static void m1(){
        System.out.println(Thread.currentThread().getName()+"   come in");
        m2();
        System.out.println(Thread.currentThread().getName()+"   end");
    }

    public static void m2(){
        System.out.println(Thread.currentThread().getName()+"   come in");
        m3();
    }

    public static void m3(){
        System.out.println(Thread.currentThread().getName()+"   come in");
    }
}
