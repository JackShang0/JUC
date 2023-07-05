package com.juc.juc01_thread.thread02_future;

import java.util.concurrent.Callable;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/11
 * @version: 1.0
 */
public class MyThreadCallable implements Callable<String> {

    /**
     *  Runnable 和 Callable 的区别
     *  1、是否抛出异常
     *  2、是否有返回值
     */
    @Override
    public String call() throws Exception {
        System.out.println(" thread.call");
        return "hello";    }
}
