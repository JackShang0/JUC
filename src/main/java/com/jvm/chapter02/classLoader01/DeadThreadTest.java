package com.jvm.chapter02.classLoader01;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class DeadThreadTest {

static class  DeadThread{
    static {
        if (true){
            System.out.println( Thread.currentThread().getName() + " 初始化当前类");
            while (true){

            }
        }
    }
}
    public static void main(String[] args) {

        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName() + " 开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + " 结束");

        };

        Thread thread1 = new Thread(r, "thread-1");
        Thread thread2 = new Thread(r, "thread-2");

        thread1.start();
        thread2.start();

    }
}
