package com.juc.juc_13_reentrantReadWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @description: StampedLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 * @author: shangqj
 * @date: 2023/10/31
 * @version: 1.0
 */
public class StampedLockDemo {

    static int number = 30;


    static StampedLock stampedLock = new StampedLock();


    public void write() {

        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t" + " 写线程准备修改");

        try {
            number = number + 20;
        } finally {
            stampedLock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + "\t" + " 写线程完成修改" + "\t" + "result ：" + number);
        }
    }


    /**
     * 悲观读，读没有完成的时候，写锁没有办法获取
     */
    public void read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t" + " 读线程准备读取");
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("正在读取中......");
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t" + " 获得成员变量result " + result);
            System.out.println("写线程没有修改成功，读锁时候写线程没有办法介入，传统读写互斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }


    public static void main(String[] args) {

        //使用stampedLock代替reentrantReadWriteLock方法的案例
        //replaceReentrantReadWrite();

        //validate：
        // 返回true 则代表，读的过程中没有修改，
        // false则表示读的过程中数据有写入修改，同时放弃本次读取的数据，并将乐观读变为悲观读，重新读取
        //tryOptimisticRead();
        optimisticRead();
    }

    /**
     * 乐观读，读取过程中，如果发现数据修改，则将乐观读改为悲观读，对修过的数据重新读取
     */
    private static void optimisticRead() {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        //乐观读
        new Thread(StampedLockDemo::tryOptimisticRead,"readThread").start();

        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //写入
        new Thread(stampedLockDemo::write,"writeThread").start();
    }

    //乐观锁，读的过程中允许写锁的介入
    public static void tryOptimisticRead() {
        //乐观读
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;

        System.out.println(Thread.currentThread().getName() + " 初始 validate " + "\t" + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "正在读取第"+i+ "秒 validate " + "\t" + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)){
            System.out.println("线程操作过数据");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从 乐观锁 升级为 悲观锁");
                result = number;
                System.out.println("重新悲观锁读后 "+ result);
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t"+ " finally value "+result);

    }


    /**
     * 案例：替代 ReentrantReadWriteLock
     * 读的过程中，写锁写入没有成功，如果写入成功，读出的数据应该是最新的50
     */
    private static void replaceReentrantReadWrite() {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        new Thread(stampedLockDemo::read, "read-thread").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            System.out.println("write Thread is come in......");
            stampedLockDemo.write();
        }, "write-thread").start();
    }

}
