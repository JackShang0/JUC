package com.jvm.chapter08;

/**
 * 添加jvm参数：-Xms10m -Xmx10m  初始化堆大小为10M，最大堆空间为10M
 */
public class HeapDemo {

    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }

}
