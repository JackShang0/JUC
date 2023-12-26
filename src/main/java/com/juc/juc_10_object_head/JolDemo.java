package com.juc.juc_10_object_head;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/9/7
 * @version: 1.0
 */
public class JolDemo {
    public static void main(String[] args) {

        //Thread.currentThread();
        //虚拟机详细的参数
        //System.out.println(VM.current().details());
        //所有的对象分配的字节都是8的整数倍
        //System.out.println(VM.current().objectAlignment());

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("------------------");

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer{
    int id;
    String name;
}
