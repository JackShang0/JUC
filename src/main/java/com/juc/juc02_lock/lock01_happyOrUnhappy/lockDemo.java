package com.juc.juc02_lock.lock01_happyOrUnhappy;

import org.aspectj.weaver.ast.Var;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/14
 * @version: 1.0
 */
public class lockDemo {

    /**
     * 悲观锁
     */
    public synchronized void lockWay01(){}


    /**
     * 悲观锁
     */
    public void lockWay02(){
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            way();
        }finally {
            reentrantLock.unlock();
        }


    }
    //业务方法
    public static void way(){}


    /**
     * 乐观锁
     */
    private AtomicInteger atomicInteger = new AtomicInteger();
    public void lockWay03(){
        atomicInteger.incrementAndGet();
    }


}
