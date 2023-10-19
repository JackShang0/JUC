package com.juc.juc06_volitale;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/7
 * @version: 1.0
 */
public class VolatileDemoAtom {

    /**
     * 针对 volatile 关键字进行使用  原子性
     * @param args
     */
    public static void main(String[] args) {
        Number number = new Number();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                for (int i1 = 1; i1 <= 1000; i1++) {
                    number.addNumber();
                }
            },String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("number = " + number.number);
    }
}

class Number{
    volatile int number ;
    /*public synchronized void addNumber(){
        number ++;
    }*/

    public void addNumber(){
        number ++;
    }
}