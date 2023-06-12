package com.juc.thread_future;

import java.util.concurrent.*;

/**
 * @description 缺点展示
 * @author: shangqj
 * @date: 2023/4/11
 * @version: 1.0
 */
public class FutureThreadPool2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {


        /**
         * 线程池的4种创建方式   todo
         */
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        /**
         * 创建线程池的核心参数   todo
         *
         */
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, null);


        //FutureTask<String> stringFutureTask1 = new FutureTask<String>();


        FutureTask<String> stringFutureTask = new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in ");
            TimeUnit.SECONDS.sleep(5);
            return "task1 over";
        });

        Thread thread = new Thread(stringFutureTask,"t1");
        thread.start();

        /**
         * 缺点一：get的方法的阻塞
         * 此处关于get方法的说明，如果
         *         System.out.println(Thread.currentThread().getName() + "忙其他的事情---");
         *
         *         System.out.println(stringFutureTask.get());
         *
         *         执行顺序是这样，则程序是可以被正常高效的执行的
         *
         *         如果执行顺序是这样
         *         System.out.println(stringFutureTask.get());
         *
         *         System.out.println(Thread.currentThread().getName() + "忙其他的事情---");
         *
         *         则程序的main程序不会执行其他任务，会等待get方法执行完成之后才会执行自己的任务
         */
        //主线程 执行其他事情
        System.out.println(Thread.currentThread().getName() + "忙其他的事情---");

        //获取线程的执行结果,容易造成程序的堵塞   一般将get放到最后面处理
        /**
         * get 小结：
         *      get容易程序堵塞
         *      假如不愿意等待很长时间，可以设置一个等待时间，超过等待时间就会抛出异常，一定程度上避免程序的阻塞
         */
        //System.out.println(stringFutureTask.get());
        //System.out.println(stringFutureTask.get(3,TimeUnit.SECONDS));       //-->设置等待时间


        /**
         * isDone 方法
         *  轮询的方式会耗费无畏的cpu资源，而且不见得能及时的处理计算结果
         *  如果想要异步获取结果，通常会以轮询的方式获取结果，尽量不要堵塞
         */
        while (true){
            if (stringFutureTask.isDone()){
                stringFutureTask.get();
                System.out.println(stringFutureTask.get());
                break;
            }else {
                //如果没有完成 则每秒钟判断一次是否完成了
                TimeUnit.SECONDS.sleep(1);
                System.out.println("任务正在执行中");
            }
        }

        if (stringFutureTask.isCancelled()){

        }

    }
}