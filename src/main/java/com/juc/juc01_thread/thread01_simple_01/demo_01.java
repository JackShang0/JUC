package com.juc.juc01_thread.thread01_simple_01;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/7
 * @version: 1.0
 */
public class demo_01 {
    public static void main(String[] args) {

        System.out.println("1------"+"\r"+"2------------");
        System.out.println("1------"+"\r"+"2------------");
        System.out.println("3------"+"\n"+"4------------");

        SimpleDateFormat sdfIntegral = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdfIntegral.format(new Date()));

        Thread thread = new Thread(()->{
            System.out.println("一个简单的线程启动咯..."+"\n");
        },"t1");
        //thread.start();


        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (o){
            }
        },"t2");
        //thread1.start();

        //false -> 用户线程
        //true -> 守护线程
        Thread threadIsDaemon = new Thread(()->{
            System.out.println("一个简单的线程启动咯..."+" \t "+Thread.currentThread().getName()+" \n "
            +(Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
            while (true){

            }
        },"t1");

        threadIsDaemon.setDaemon(false);
        threadIsDaemon.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\n"+"end 主线程");
    }


}
