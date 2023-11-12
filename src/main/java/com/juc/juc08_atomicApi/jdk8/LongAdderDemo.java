package com.juc.juc08_atomicApi.jdk8;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/22
 * @version: 1.0
 */
public class LongAdderDemo {
    public static void main(String[] args) {

        LongAdder longAdder = new LongAdder();
        //+1
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.sum());


        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 1);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(2);

        System.out.println(longAccumulator.get());


    }
}
