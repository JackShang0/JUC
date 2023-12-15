package com.jvm.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/12
 * @version: 1.0
 */
public class HeapSpaceInitial {
    /**
     * 1. -Xms 用来设置堆空间（新生代+老年代）的初始内存大小
     *      -X 是jvm的运行参数
     *      ms 是memory start
     *      -Xmx用来设置堆空间（新生代+老年代）的最大内存大小
     * 2. 默认堆空间大小
     *      初始内存大小：物理电脑内存大小的 / 64
     *      最大内存大小：物理电脑内存大小 / 4
     * 3.手动设置  -Xms600m -Xmx600m
     *      开发中建议将堆内存和最大的堆内存设置成相同的值
     * @param args a
     */
    public static void main(String[] args) {

        //返回java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("initialMemory = " + initialMemory +"M");
        System.out.println("maxMemory = " + maxMemory+"M");

/*        long memory = initialMemory * 64 /1024;
        long memory2 = maxMemory * 4 /1024;

        System.out.println("memory = " + memory+"G");
        System.out.println("memory2 = " + memory2+"G");*/

        try {
            TimeUnit.SECONDS.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
