package com.juc.juc01_thread.thread03_completableFuture;


import java.util.concurrent.*;

/**
 * @description 入门使用
 * @author: shangqj
 * @date: 2023/4/12
 * @version: 1.0
 */
public class CompletableFuture_01 {

    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);
    static ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();

        /**
         * 返回结果
         * ForkJoinPool.commonPool-worker-1
         * null
         */
        runAsync();

        System.out.println("\n");

        runAsyncPool();

        System.out.println("\n");

        supplyAsync();

        /*CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync --> CompletableFuture";
        },fixedThreadPool);

        System.out.println(stringCompletableFuture.GetApi());
        fixedThreadPool.shutdown();*/

    }

    /**
     * 无返回值  不使用线程池
     * @throws ExecutionException
     * @throws InterruptedException
     * 返回结果
     *      ForkJoinPool.commonPool-worker-1
     *      null
     */
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(voidCompletableFuture.get());
    }


    /**
     * 无返回值，指定线程池
     * 返回结果
     *      pool-1-thread-1
     *      null
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void runAsyncPool() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, fixedThreadPool);

        System.out.println(voidCompletableFuture.get());

        fixedThreadPool.shutdown();
    }


    /**
     * 输出结果
     *      ForkJoinPool.commonPool-worker-1        使用线程池 输出结果 pool-2-thread-1
     *      hello supplyAsync --> CompletableFuture
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync --> CompletableFuture";
        },fixedThreadPool2);

        System.out.println(stringCompletableFuture.get());
        fixedThreadPool2.shutdown();
        return stringCompletableFuture.get();
    }
}
