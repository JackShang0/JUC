package com.juc.juc_11_synchronized;

/**
 * @description: 锁 粗化
 * @author: shangqj
 * @date: 2023/10/23
 * @version: 1.0
 */
public class LockWideDemo {


    static Object object = new Object();

    /**
     * 假如方法中的锁，首位相连，前后都是同一个锁对象，编译器就会把这几个锁的业务处理做合并，加粗加大锁的范围
     * 是编译器底层的一种优化，可以减少锁的申请和释放
     * @param args  a
     */
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (object){
                System.out.println("1");
            }
            synchronized (object){
                System.out.println("2");
            }
            synchronized (object){
                System.out.println("3");
            }

            synchronized (object){
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
            }
        },"t").start();

    }
}
