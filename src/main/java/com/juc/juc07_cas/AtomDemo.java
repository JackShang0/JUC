package com.juc.juc07_cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/8
 * @version: 1.0
 */
public class AtomDemo {

    AtomicInteger atomicInteger = new AtomicInteger();

    public int getAtomicInteger(){
        return atomicInteger.get();
    }

    public int setAtomicInteger(){
        return atomicInteger.getAndIncrement();
    }
}
