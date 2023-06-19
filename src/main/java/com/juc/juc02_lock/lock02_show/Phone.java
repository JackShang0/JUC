package com.juc.juc02_lock.lock02_show;

import java.util.concurrent.TimeUnit;

/**
 * @description 资源类，类中方法加锁之后，同一时间只有一个线程能进入到资源类中调用方法
 * @author: shangqj
 * @date: 2023/6/15
 * @version: 1.0
 */
public class Phone {


    public static synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send E-mail");
    }

    public synchronized void sendSms(){

        //同步代码块
        synchronized (this){

        }

        System.out.println("send SMS");
    }

    public  void hello(){
        System.out.println("say hello");
    }
}
