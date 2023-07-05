package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.*;

/**
 * @description
 * @author: shangqj
 * @date: 2023/5/6
 * @version: 1.0
 */
public class ThenApply {
    public static void main(String[] args) {

        //BlockingQueue<Runnable> strings = new ArrayBlockingQueue<>(10, true);
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1000L, TimeUnit.MINUTES, strings);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        },executorService).thenApply(integer -> {
            System.out.println("1");
            return integer + 1;})
                .thenApply(integer -> {
                    //int i = 10/0;
                    System.out.println("2");
                    return integer + 1;})
                .thenApply(f -> {
                    System.out.println("3");
                    return f + 1;})
                .whenComplete((integer, throwable) -> {
                    if (throwable==null){
                        System.out.println("result="+integer);
                    }
                })
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    System.out.println(throwable.getMessage());
                    return null;
                });

        System.out.println(integerCompletableFuture.join());
        executorService.shutdown();


    }
}
