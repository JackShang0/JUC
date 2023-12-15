package com.jvm.chapter16.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用的测试
 */
public class PhantomReferenceTest {

    public static PhantomReferenceTest obj; //当前类对象的声明
    static ReferenceQueue<PhantomReferenceTest> phantomQueue = null; //引用队列

    /**
     * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象后，
     * 将这个虚引用加入引用队列，以通知应用程序对象的回收情况。
     * 这个线程就是来操作引用队列的
     */
    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                //第2次GC的时候将obj对象回收了，此时引用队列就会存在虚引用了
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceTest> objt = null;
                    try {
                        //从引用队列里面取出虚引用
                        objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceTest实例被GC了");
                    }
                }
            }
        }
    }

    /**
     * 垃圾回收之前会先调用finalize()
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable { //finalize()方法只能被调用一次！
        super.finalize();
        System.out.println("调用当前类的finalize()方法");
        obj = this; //让当前对象重新被引用
    }

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true); //设置为守护线程：当程序中没有非守护线程时，守护线程也就执行结束。
        t.start();

        phantomQueue = new ReferenceQueue<PhantomReferenceTest>(); //实例化引用队列
        obj = new PhantomReferenceTest(); //实例化当前类对象
        //构造了 PhantomReferenceTest 对象的虚引用，并指定了引用队列
        PhantomReference<PhantomReferenceTest> phantomRef = new PhantomReference<PhantomReferenceTest>(obj, phantomQueue);

        try {
            //不可获取虚引用中的对象
            System.out.println(phantomRef.get()); //null

            //将强引用去除
            obj = null;
            //第一次进行GC,由于对象可复活，GC无法回收该对象
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用"); //最终执行这一句
            }
            System.out.println("第 2 次 gc");
            obj = null;
            System.gc(); //一旦将obj对象回收，就会将此虚引用存放到引用队列中。
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null"); //最终执行这一句
            } else {
                System.out.println("obj 可用");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
