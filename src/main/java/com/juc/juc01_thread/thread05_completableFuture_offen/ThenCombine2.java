package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.CompletableFuture;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/12
 * @version: 1.0
 */
public class ThenCombine2 {
    public static void main(String[] args) {

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + " come in 1");
            return 1;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in 2");
            return 2;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in 3");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in 4");
            return 4;
        }), (a, b) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in 5");
            return a + b;
        });

        System.out.println("integerCompletableFuture.join() = " + integerCompletableFuture.join());
    }
}
