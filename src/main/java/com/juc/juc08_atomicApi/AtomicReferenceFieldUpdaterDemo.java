package com.juc.juc08_atomicApi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/21
 * @version: 1.0
 */
public class AtomicReferenceFieldUpdaterDemo {
    /**
     * 需求：
     * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作
     * 要求只能被初始化一次，只有一个线程操作成功
     * 例如：早上到公司只有第一个员工需要开灯，后续员工无需再开灯
     */
    public static void main(String[] args) {
        Flag flag = new Flag();
        for (int i = 1; i <= 3; i++) {
            new Thread(()->{
                flag.init(flag);
            },String.valueOf(i)).start();

        }
    }
}

class Flag{
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<Flag,Boolean> fieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Flag.class, Boolean.class,"isInit");

    public void init(Flag flag)  {
        if (fieldUpdater.compareAndSet(flag,Boolean.FALSE,Boolean.TRUE)) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("   初始化为 true");
            System.out.println(Thread.currentThread().getName() + "\t" + flag.isInit);
        }else {

            System.out.println("   不需要初始化  已经是true");
            System.out.println(Thread.currentThread().getName() + "\t" + flag.isInit);
        }
    }
}
