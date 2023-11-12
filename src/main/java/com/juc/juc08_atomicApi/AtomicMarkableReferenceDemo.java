package com.juc.juc08_atomicApi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @description: AtomicMarkableReference
 * @author: shangqj
 * @date: 2023/8/17
 * @version: 1.0
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100,false);
    public static void main(String[] args) {
        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = atomicMarkableReference.compareAndSet(atomicMarkableReference.getReference(), 1000, marked, !marked);
            System.out.println("t1 flag = " + b);
        },"t1").start();

        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = atomicMarkableReference.compareAndSet(atomicMarkableReference.getReference(), 2000, marked, !marked);
            System.out.println("t2 flag = " + b);
            Object reference = atomicMarkableReference.getReference();
            System.out.println("reference = " + reference);
        },"t2").start();

    }
}
