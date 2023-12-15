package com.jvm.chapter08;

/**
 * 同步省略说明
 */
public class SynchronizedTest {
    public void f() {
        Object hellis = new Object();
        synchronized(hellis) {
            System.out.println(hellis);
        }
    }
}
