package com.juc.juc_10_object_head;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/9/7
 * @version: 1.0
 */
public class ObjectDemo {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(o.hashCode());
        //对象被锁的次数在哪里记录
        synchronized (o){

        }
        //对象超过15次gc还未被回收的对象，会由新生代转为老年代对象，那么在哪里记录gc次数呢？
        System.gc();
    }
}