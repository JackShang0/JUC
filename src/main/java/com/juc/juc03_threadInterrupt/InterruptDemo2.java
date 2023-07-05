package com.juc.juc03_threadInterrupt;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class InterruptDemo2 {


    public static void main(String[] args) {

        //实例方法 interrupt() 仅仅是设置线程的中断状态是true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("---->" + i);
            }
        }, "t1");
        t1.start();

        System.out.println("线程的默认中断标识为"+t1.isInterrupted());

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

        System.out.println("调用 interrupt 方法后线程的中断标识为"+t1.isInterrupted());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("2秒后 后线程的中断标识为"+t1.isInterrupted());
    }
}
