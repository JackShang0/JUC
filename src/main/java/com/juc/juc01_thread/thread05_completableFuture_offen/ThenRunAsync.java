package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/5/31
 * @version: 1.0
 */
public class ThenRunAsync {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            //CompletableFuture.supplyAsync(()->{return 1;},executorService).
            System.out.println(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
                return "1";
            },executorService).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            }).get(2L, TimeUnit.SECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch (Exception e){

        }finally {
            executorService.shutdown();
        }
    }
}
