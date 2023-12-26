package com.juc.juc_12_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: AQS线程抢占 案例说明
 * @author: shangqj
 * @date: 2023/10/24
 * @version: 1.0
 */
public class AqsApiDemo {
    public static void main(String[] args) {


        //ReentrantLock reentrantLock = new ReentrantLock();
        //非公平锁
        ReentrantLock reentrantLock = new ReentrantLock(false);

        // ABC 三个顾客去银行办理业务，A先到，窗口此时没有人，他优先办理业务
        //A耗时严重，长时间占有窗口
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(" Thread A  come in ...");
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                reentrantLock.unlock();
            }
        },"A").start();


        // B 是第二个顾客，看到窗口被占用，进入AQS队列中等待，等待A办理业务完成，尝试去抢占窗口
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(" Thread B  come in ...");

            }finally {
                reentrantLock.unlock();
            }
        },"B").start();


        // C 是第三个顾客，C看到窗口被A占用着，进入AQS队列，等待A办理完成，尝试去抢占窗口，前面顾客是B FIFO
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(" Thread C  come in ...");

            }finally {
                reentrantLock.unlock();
            }
        },"C").start();

    }
}
