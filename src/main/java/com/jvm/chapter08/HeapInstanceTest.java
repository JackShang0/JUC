package com.jvm.chapter08;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];

    /**
     * 持续往内存中添加对象，当Eden满了的时候会触发YoungGC，YongGC后会把对象放到幸存者to区，to区放不下会直接放到老年代中
     * 重复以上过程，直到老年代放不下，报出oom
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> list = new ArrayList<HeapInstanceTest>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
