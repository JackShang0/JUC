package com.juc.juc01_thread.thread03_completableFuture;

import com.juc.juc01_thread.thread02_future.MyThreadRunnable;
import java.util.concurrent.*;

/**
 * @description 减少轮询和阻塞
 * @author: shangqj
 * @date: 2023/4/12
 * @version: 1.0
 */
public class CompletableFuture_02 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //基础的future的功能是可以实现的
        baseFuture();

        System.out.println("=========");

        //
        baseCompletableFuture();
    }

    /**
     * 使用 CompletableFuture 来实现 future 的基础功能
     * @return 随机数 < 10
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Integer baseFuture() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new MyThreadRunnable());
        //supplier 接口使用练习
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new MyThreadSupply());

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "--come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结果为  " + result);
            return result;

        });
        System.out.println(Thread.currentThread().getName()+"--先去忙其他的事情");

        System.out.println(integerCompletableFuture.get());
        return integerCompletableFuture.get();

    }


    /**
     * 使用 completableFuture 回调
     * @return 返回数值
     * @throws ExecutionException e
     * @throws InterruptedException e
     */
    public static Integer baseCompletableFuture() throws ExecutionException, InterruptedException {

        /**
         * 注意：一般都会创建用户线程来使用，为了避免main线程执行结束导致的forkJoinPool的守护线程关闭的问题
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> integerCompletableFuture = null;
        try {

             integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "--come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结果为  " + result);

                //异常情况
                if (result>5){
                    int i = 10/0;
                }
                return result;

                //不会阻塞线程任务的执行，当线程执行完成之后可以直接对结果进行处理
            },executorService).whenComplete((v,e)->{
                if (e == null){
                    System.out.println("输出结果为 --"+ v);
                }
                //异常处理
            }).exceptionally((e)->{
                e.printStackTrace();
                System.out.println("异常信息-"+ e.getCause()+"\t"+e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName()+"--先去忙其他的事情");


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

        return integerCompletableFuture.get();
    }
}
