package com.juc.juc01_thread.thread05_completableFuture_offen;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
/**
 * @description
 * @author: shangqj
 * @date: 2023/6/7
 * @version: 1.0
 */
public class ApplyToEither {
    public static void main(String[] args) {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A  come in ...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "play A";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B  come in ...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "play B";
        });

        /**
         * 判断两个任务谁先执行成功，可以用 applyToEither 这个方法
         */
        CompletableFuture<String> stringCompletableFuture = playA.applyToEither(playB, f -> {
            return f + " is winer";
        });

        System.out.println(Thread.currentThread().getName()+"\t"+stringCompletableFuture.join());

    }
}
