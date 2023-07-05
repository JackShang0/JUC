package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.CompletableFuture;

/**
 * @description
 * @author: shangqj
 * @date: 2023/5/31
 * @version: 1.0
 */
public class ThenAccept {
    public static void main(String[] args) {
        //CompletableFuture<Void> voidCompletableFuture =
                /*CompletableFuture.supplyAsync(() -> {return 1;})
                .thenApply( s -> s+1)
                .thenApply( s -> s+1)
                //thenAccept 消费处理，无返回结果
                .thenAccept(System.out::println);*/


        //thenRun(Runnable r)
        //CompletableFuture.supplyAsync(()-> "resultA").thenRun(()-> System.out.println("resultB")).join();
        CompletableFuture.supplyAsync(()-> "resultA").thenRun(()-> {}).join();

        //thenAccept(Consumer action)  使用异步线程的执行结果resultB,并且没有有返回值
        CompletableFuture.supplyAsync(()-> "resultB").thenAccept(System.out::println).join();

        //thenApply(Function fn)    使用resultC的结果并且有返回值
        System.out.println(CompletableFuture.supplyAsync(() -> "resultC").thenApply(s -> {
            //System.out.println(s);
            return s + "123";
        }).join());

    }
}
