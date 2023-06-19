package com.juc.juc02_lock.lock05_reTryLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description  显式锁
 * @author: shangqj
 * @date: 2023/6/19
 * @version: 1.0
 */
public class ReEntryLockDemo02 {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"  外层 come in");
                lock.lock();
                try {

                    System.out.println(Thread.currentThread().getName() +"  中层 come in");
                    lock.lock();
                    try {

                        System.out.println(Thread.currentThread().getName() +"  内层 come in");

                    }finally {
                        lock.unlock();
                    }
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() +"  end");
            }finally {
                lock.unlock();
            }
        },"lock1").start();


        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() +"  外层 come in");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() +"  中层 come in");
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() +"  end");
            }finally {
                lock.unlock();
            }
        },"lock2").start();
    }
}
