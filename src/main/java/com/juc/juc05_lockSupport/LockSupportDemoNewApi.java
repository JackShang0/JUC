package com.juc.juc05_lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description
 * @author: shangqj
 * @date: 2023/7/7
 * @version: 1.0
 */
public class LockSupportDemoNewApi {
    public static void main(String[] args) {

        LockSupportNewApi();
    }

    private static void LockSupportNewApi() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + " 开始阻塞");
            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + "\t"+" 解除阻塞完成");

        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t" + " 发起释放通知");
            LockSupport.unpark(t1);

        },"t2").start();
    }
}
