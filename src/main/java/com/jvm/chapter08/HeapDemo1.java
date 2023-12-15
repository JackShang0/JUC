package com.jvm.chapter08;

/**
 * 添加jvm参数：-Xms20m -Xmx20m  初始化堆大小为20M，最大堆空间为20M
 */
public class HeapDemo1 {

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
