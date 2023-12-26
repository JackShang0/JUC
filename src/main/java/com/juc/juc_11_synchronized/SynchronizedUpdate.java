package com.juc.juc_11_synchronized;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 锁升级
 * @author: shangqj
 * @date: 2023/9/12
 * @version: 1.0
 */
public class SynchronizedUpdate {
    public static void main(String[] args) {
        //new Thread(()->{},"t1").start();
        Object o = new Object();
        System.out.println("10进制:"+o.hashCode());
        System.out.println("16进制:"+Integer.toHexString(o.hashCode()));
        System.out.println("2进制:"+ Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
