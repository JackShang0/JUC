package com.juc.juc02_lock.lock04_fairOrUnfair;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/18
 * @version: 1.0
 */
public class ReentrantLockDemo {

    /**
     * 模拟三个售票员卖完50张票
     * @param args
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{ for(int i =0;i<55;i++) {ticket.sale();}},"a").start();
        new Thread(()->{ for(int i =0;i<55;i++) {ticket.sale();}},"b").start();
        new Thread(()->{ for(int i =0;i<55;i++) {ticket.sale();}},"c").start();
    }

}

class Ticket {
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);
    public void sale(){
        lock.lock();
        try {
            if (number>0){
                number--;
                System.out.println(Thread.currentThread().getName() +" Ticket sale... "+(50-number)+"  还剩"+number);
            }
        }finally {
            lock.unlock();
        }
    }
}
