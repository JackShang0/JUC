package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/12
 * @version: 1.0
 */
public class ThenCombine {
    public static void main(String[] args) {
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"启动了");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });


        CompletableFuture<Integer> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"启动了");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

        Integer join = stringCompletableFuture.thenCombine(stringCompletableFuture2, (x, y) -> {
            System.out.println("结果合并---" + x + "----" + y);
            return x + y;
        }).join();
        System.out.println("join = " + join);

    }
}
