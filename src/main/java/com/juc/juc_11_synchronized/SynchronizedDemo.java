package com.juc.juc_11_synchronized;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description:  偏向锁的案例和使用
 * @author: shangqj
 * @date: 2023/10/19
 * @version: 1.0
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        //-XX:BiasedLockingStartupDelay=0
        //保障偏向锁启动的两种方式
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println("-----------------");

        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }
}
