package com.juc.thread_completableFuture;

import java.util.function.Supplier;

/**
 * @description: supplier接口
 * @author: shangqj
 * @date: 2023/4/13
 * @version: 1.0
 */
public class MyThreadSupply implements Supplier<String> {
    @Override
    public String get() {
        return "hello suppiler";
    }
}
