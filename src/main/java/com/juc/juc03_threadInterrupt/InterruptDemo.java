package com.juc.juc03_threadInterrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class InterruptDemo {

    static volatile Boolean flag = false;

    static AtomicBoolean aBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        //m1_volatile();
        //m2_atomicBoolean();
        m3_interruptApi();
    }

    /**
     * 使用中断api实现
     */
    private static void m3_interruptApi() {
        Thread t3 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+" isInterrupted() 被修改为true 程序停止");
                    break;
                }
                System.out.println("hello t3");
            }
        }, "t3");
        t3.start();

        try {
            TimeUnit.NANOSECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //两种二选一都可以
        new Thread(()->{
            t3.interrupt();
        },"t2").start();
        //t3.interrupt();
    }

    /**
     * 使用原子类  原子类相当于是加了锁的类型
     */
    private static void m2_atomicBoolean() {
        new Thread(()->{
            while (true){
                if (aBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "AtomicBoolean被修改 -->"+flag);
                    break;
                }
                System.out.println("------ hello AtomicBoolean");
            }
        },"t1").start();

        try {
            TimeUnit.NANOSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            aBoolean.set(true);
        },"t2").start();
    }

    /**
     * volatile 可见性，通过线程对可见性的关键字进行判断，从而使得线程停止
     */
    private static void m1_volatile() {
        new Thread(()->{
            while (true){
                if (flag){
                    System.out.println(Thread.currentThread().getName() + "falg被修改-->"+flag);
                    break;
                }
                System.out.println("------ hello volatile");
            }
        },"t1").start();

        try {
            TimeUnit.NANOSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            flag = true;
        },"t2").start();
    }
}
