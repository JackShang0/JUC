package com.juc.juc_09_threadLocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.*;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/9/4
 * @version: 1.0
 */
public class ThreadLocalApi2 {

    /**
     * 使用线程池和自定义ThreadLocal变量，尤其是在线程池场景下，线程经常被重复使用，如果不清理自定义的 ThreadLocal 变量，可能会影响业务
     * 要尽量在代码的 try-finally 中remove ThreadLocal的值
     */
    static ThreadFactory factory = new ThreadFactoryBuilder().build();
    public static void main(String[] args) {
        //Executors.newFixedThreadPool()
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(Runtime.getRuntime().availableProcessors() * 20),
                factory);

        MyData myData = new MyData();

        try {
            for (int i = 1; i <= 10; i++) {
                poolExecutor.submit(()->{
                    try {
                        Integer integer = myData.threadLocal.get();
                        myData.add();
                        Integer integer1 = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() +"\t before "+ integer+"  \t  after  \t"+integer1);
                    }finally {
                        //myData.threadLocal.remove();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            poolExecutor.shutdown();
        }
    }
}

class MyData{
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
    public void add(){
        threadLocal.set(1+threadLocal.get());
    }
}
