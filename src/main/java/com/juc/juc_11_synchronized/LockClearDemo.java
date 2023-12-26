package com.juc.juc_11_synchronized;

/**
 * @description: 锁消除 案例
 * @author: shangqj
 * @date: 2023/10/23
 * @version: 1.0
 */
public class LockClearDemo {
    /**
     * 对于一些加锁不太合理的情况，编译器会自行优化，某些对象加了锁，但是从对象底层的机器码来讲，锁并没有加，消除了锁的使用
     */
    static Object lock = new Object();

    public void m1(){
        //锁消除问题   JIT编译器会无视他
        Object o = new Object();
        synchronized (o){
            System.out.println("---hello  LockClear...  \t"+o.hashCode() + "\t"+ lock.hashCode());
        }
    }
    public static void main(String[] args) {
        LockClearDemo clearDemo = new LockClearDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(clearDemo::m1,String.valueOf(i)).start();
        }
    }
}
