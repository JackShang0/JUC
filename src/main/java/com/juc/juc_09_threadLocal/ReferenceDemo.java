package com.juc.juc_09_threadLocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @author: shangqj
 * @date: 2023/9/6
 * @version: 1.0
 */
public class ReferenceDemo {

    private static int a = 1;
    public static void main(String[] args) {

        System.out.println(a);
        phantomReference();

        //weakReference();

        //softReference();

        //strongReference();

    }

    /**
     * 虚引用：
     *  1、和没有引用对象一样，在任何情况下都可能被垃圾回收器回收，需要和联合队列使用
     *  2、如果一个虚引用对象被垃圾回收，则会将垃圾回收的对象放到 联合对立队列中
     */
    private static void phantomReference() {
        /*
          执行结果：
             null
          null	   list add success
          --  finalize  --
          null	   list add success
          null	   list add success
          null	   list add success
          null	   list add success
          null	   list add success
          Exception in thread "t1" java.lang.OutOfMemoryError: Java heap space
          	at com.juc.juc_09_threadLocal.ReferenceDemo.lambda$main$0(ReferenceDemo.java:28)
          	at com.juc.juc_09_threadLocal.ReferenceDemo$$Lambda$1/314265080.run(Unknown Source)
          	at java.lang.Thread.run(Unknown Source)
           已经有虚引用的对象被回收  加入了队列
         */
        MyObject myObject = new MyObject();

        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        //虚引用的 构造方法  引用对象 + 引用队列
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);

        //get方法总是返回 null
        System.out.println("   "+phantomReference.get());
        ArrayList<byte[]> list = new ArrayList<>();

        // 每500毫秒add 1m 数据到list中，直至oom
        new Thread(()->{
            while (true){
                list.add(new byte[1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(phantomReference.get()  +"\t   list add success ");
            }
        },"t1").start();

        //如果有虚引用对象被垃圾回收，则虚引用的队列不会为空
        new Thread(()->{
            while (true){
                Reference<? extends MyObject> poll = referenceQueue.poll();
                if (poll!= null){
                    System.out.println(" 已经有虚引用的对象被回收  加入了队列   ");
                    break;
                }
            }
        },"t2").start();
    }

    /**
     * 弱引用：
     * 对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否够用，都会回收该对象占用的内存
     */
    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("   ge before   内存够用： \t"+weakReference.get());
        System.gc();
        System.out.println("   ge after   内存够用： \t"+weakReference.get());
    }

    /**
     * 软引用：
     *  1、当系统内存充足时，不会被垃圾回收；当系统内存不足时，会被垃圾回收
     *  2、通常在对内存敏感的内存中，比如高速缓存中，内存不足的时候会回收弱引用的对象
     */
    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("soft   \t"+softReference.get());
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("当前内存够用   \t"+softReference.get());

        try {
            //提前设置 jvm 启动内存和最大内存为 10m
            //创建一个 20MB 的对象
            Byte[] bytes = new Byte[20 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("当前内存不够用   \t"+softReference.get());
        }
    }

    /**
     * 强引用：
     * 1、是最常见的普通对象引用，只要强引用还指向一个对象，就表明对象还活着，不会被垃圾回收，无论它是否被使用
     * 2、强引用的对象可达性分析为可达状态，不会被强制回收
     * 3、强引用的对象，就算出现内存溢出也不会回收该对象
     */
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println(" myObject before  \t"+myObject);
        myObject = null;
        System.gc();
        System.out.println("myObject  after   \t"+myObject);
    }
}



class MyObject{

    //对象回收之前会调用这个方法  一般不会重写该方法，此处只是为了结合案例使用
    @Override
    protected void finalize() throws Throwable {
        System.out.println("--  finalize  --");
    }
}