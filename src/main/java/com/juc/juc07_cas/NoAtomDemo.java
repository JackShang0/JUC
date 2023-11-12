package com.juc.juc07_cas;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/8
 * @version: 1.0
 */
public class NoAtomDemo {
    volatile int number = 0;
    public int getNumber(){
        return number;
    }

    public synchronized void setNumber(){
        number ++;
    }

    public static void main(String[] args) {

    }
}
