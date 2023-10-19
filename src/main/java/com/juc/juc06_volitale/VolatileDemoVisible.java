package com.juc.juc06_volitale;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/7
 * @version: 1.0
 */
public class VolatileDemoVisible {

    //static boolean flag = true;
    static volatile boolean flag = true;

    /**
     * 针对 volatile 关键字进行使用  可见性
     * @param args
     */
    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " come in...");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName() + " come out...");
        },"t1").start();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //main 线程 修改状态
        flag = false;
        System.out.println(Thread.currentThread().getName() + " 修改完成");

    }
}
