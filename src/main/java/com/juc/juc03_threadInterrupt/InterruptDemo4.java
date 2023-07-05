package com.juc.juc03_threadInterrupt;


import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class InterruptDemo4 {


    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        //测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态
        //第二次再调用时中断状态已经被清除，将返回一个false
        System.out.println(Thread.currentThread().getName()+"\n\t"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\n\t"+Thread.interrupted());

        System.out.println("------------->");
        Thread.currentThread().interrupt();

        System.out.println(Thread.currentThread().getName()+"\n\t"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\n\t"+Thread.interrupted());

        //静态方法   底层调用isInterrupted方法
        Thread.interrupted();

        //实例方法
        Thread.currentThread().isInterrupted();
        //WorkQueue workQueue = new WorkQueue();
        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(100);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,5,10, TimeUnit.SECONDS,null
        );
    }
}
