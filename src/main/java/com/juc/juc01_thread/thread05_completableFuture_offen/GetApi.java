package com.juc.juc01_thread.thread05_completableFuture_offen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
/**
 * @description
 * @author: shangqj
 * @date: 2023/4/20
 * @version: 1.0
 */
public class GetApi {

    /**
     * 获取结果
     * get()
     * get(long timeout,TimeUtils unit)
     * join()
     * getNow(T valueIfAbsent)
     *
     * complete 返回 boolean
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() ->
                {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "--->";
                }
        );

        System.out.println(stringCompletableFuture.get());
        System.out.println(stringCompletableFuture.get(1000, TimeUnit.MILLISECONDS));
        System.out.println(stringCompletableFuture.join());
        //立刻就要获取到执行结果，如果没有执行结果则返回valueIfAbsent这个默认值
        System.out.println(stringCompletableFuture.getNow("12"));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //CompletableFuture.completedFuture("xxx")+"\t\n"+CompletableFuture.
        System.out.println(stringCompletableFuture.complete("xxx") + "\n" + stringCompletableFuture.join());


    }
}