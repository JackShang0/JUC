package com.juc.juc_11_synchronized;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 轻量级锁
 * @author: shangqj
 * @date: 2023/10/19
 * @version: 1.0
 */
public class SynchronizedLightDemo {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println("本应该是偏向锁");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
        o.hashCode();

        new Thread(()->{
            synchronized (o){
                System.out.println("本应该是偏向锁,但是hashcode方法调用过后，会升级为轻量级锁");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t2").start();

    }
}
