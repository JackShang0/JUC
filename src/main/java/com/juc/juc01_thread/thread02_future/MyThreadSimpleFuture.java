package com.juc.juc01_thread.thread02_future;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/11
 * @version: 1.0
 */
public class MyThreadSimpleFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //MyThreadCallable myThreadCallable = new MyThreadCallable();
        FutureTask<String> callableFutureTask = new FutureTask(new MyThreadCallable());
        //FutureTask callableFutureTask = new FutureTask(new MyThreadCallable());
        //FutureTask<String> callableFutureTask = new FutureTask<>(new MyThread_02());

        Thread t1 = new Thread(callableFutureTask, "t1");
        t1.start();

        String s = callableFutureTask.get();
        System.out.println("s = " + s);

    }

}

//class MyThread_02 implements Callable<String>{
//    @Override
//    public String call() throws Exception {
//        System.out.println(" thread.call");
//        return "hello";
//    }
//}



