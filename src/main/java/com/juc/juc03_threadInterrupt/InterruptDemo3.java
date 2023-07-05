package com.juc.juc03_threadInterrupt;


import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class InterruptDemo3 {

    /**
     * 执行流程：
     * 1、t1默认中断标志位 为 false
     * 2、t2调用 t1.interrupt 标志位变为true
     * 3、此时正常情况下应该进行线程中断，跳出循环，但是由于线程调用sleep方法会对标志位进行清除，所以标志位再次变成 false
     * 4、中断标志位为 false 无限循环，需要在catch中对标志位设置为true，2次调用停止程序
     * @param args
     */
    public static void main(String[] args) {

        /*CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "↑");
        System.out.println("stringCompletableFuture.join() = " + stringCompletableFuture.join());*/



        //实例方法 interrupt() 仅仅是设置线程的中断状态是true，不会停止线程
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("此时中断标识位为："+Thread.currentThread().isInterrupted());
                    break;
                }
                try {
                    //Thread.currentThread().wait(200);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //key : 在异常处理的地方 进行中断处理，使得代码跳出死循环
                    //sleep会清除中断标志位，需要第二次将中断标志位设置为true才可以跳出循环
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("hello demo03");
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            t1.interrupt();
        }, "t2");
        t2.start();
    }
}
