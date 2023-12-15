package com.jvm.chapter17;

import java.util.ArrayList;

/**
 * @description:  -Xms60M -Xmx60M -XX:+PrintGCDetails
 * @author: shangqj
 * @date: 2023/12/14
 * @version: 1.0
 */
public class GcLogTest {
    public static void main(String[] args) {

        ArrayList<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            byte[] bytes = new byte[1024 * 100];
            list.add(bytes);

        }

    }
}
