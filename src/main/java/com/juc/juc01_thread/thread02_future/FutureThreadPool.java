package com.juc.juc01_thread.thread02_future;

import java.util.concurrent.*;

/**
 * @description  优点展示
 * @author: shangqj
 * @date: 2023/4/11
 * @version: 1.0
 */
public class FutureThreadPool {
    /**
     * 3个任务，开启多个异步任务来处理，耗时多久
     *      不获取结果   花费时间-->1050  效率显著提升
     *      获取结果     花费时间-->3048  效率能提升1/3左右
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws Exception {

        //花费时间-->4003
        //threadWay();
        long startTime = System.currentTimeMillis();
        //创建线程池，避免多个任务需要多次new 线程导致的资源的浪费，最好使用线程池使得线程做到线程的复用
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //Query query = new Query();
        //核心参数   todo
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, (BlockingQueue<Runnable>) query);

        FutureTask<String> stringFutureTask = new FutureTask<String>(()->{
            TimeUnit.SECONDS.sleep(2);
            return "task1 over";
        });
        executorService.submit(stringFutureTask);

        //System.out.println("1--"+stringFutureTask.GetApi());

        FutureTask<String> stringFutureTask2 = new FutureTask<String>(()->{
            TimeUnit.SECONDS.sleep(1);
            return "task2 over";
        });
        executorService.submit(stringFutureTask2);

        System.out.println("1-- " + stringFutureTask.get());
        System.out.println("2-- " + stringFutureTask2.get());

        /*MyThreadCallable myThreadCallable = new MyThreadCallable();
        Future<String> submit = executorService.submit(myThreadCallable);
        System.out.println("3--"+submit.GetApi());*/

        TimeUnit.SECONDS.sleep(1);

        long endTime = System.currentTimeMillis();

        System.out.println("花费时间-->"+(endTime-startTime));
        System.out.println(Thread.currentThread().getName()+"\t"+"---end");

        executorService.shutdown();
    }

    /**
     * 3个任务，只有一个main来处理，耗时多久  花费时间-->4003
     * @throws Exception
     */
    public static void threadWay() throws Exception{
        long startTime = System.currentTimeMillis();

        TimeUnit.SECONDS.sleep(2);
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);

        long endTime = System.currentTimeMillis();

        System.out.println("花费时间-->"+(endTime-startTime));
        System.out.println(Thread.currentThread().getName()+"\t"+"---end");
    }
}
