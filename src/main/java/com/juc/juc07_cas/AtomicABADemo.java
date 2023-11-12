package com.juc.juc07_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/16
 * @version: 1.0
 */
public class AtomicABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

    //new AtomicMarkableReference<Integer> atomicMarkableReference;


    /**
     * ABA问题复现
     * @param args a
     */
    public static void main(String[] args) {

        //ABA();
        ABA2();

    }

    private static void ABA() {
        new Thread(()->{
            atomicInteger.compareAndSet(100,110);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.compareAndSet(110,100);
        },"A").start();


        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicInteger.compareAndSet(100,2023)+"\t"+atomicInteger.get());

        },"B").start();
    }



    private static void ABA2() {
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t"+"首次版本号  "+ stamp);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            atomicStampedReference.compareAndSet(100,110,atomicStampedReference.getStamp() ,atomicStampedReference.getStamp()+1);

            System.out.println(Thread.currentThread().getName() + "\t"+"第二次流水号  "+ atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(110,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t"+"第三次流水号  "+ atomicStampedReference.getStamp());
        },"C").start();


        new Thread(()->{

            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t"+"首次版本号  "+ stamp);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicStampedReference.compareAndSet(100,2023,stamp,stamp+1)+"\t"+atomicInteger.get());
            //System.out.println();

        },"D").start();
    }
}
