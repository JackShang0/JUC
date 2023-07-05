package com.juc.juc04_threadPool;


import com.juc.juc03_threadInterrupt.ThreadPoolTest;

import java.util.concurrent.*;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/20
 * @version: 1.0
 */
public class ThreadPoolDemo {


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Executors创建线程池
         * 1、创建固定长度的线程池
         * 2、创建单个线程的线程池
         *      既然是单个线程的线程池，为何不直接使用线程？
         *          它可以复用线程；并且单线程的线程池提供了任务队列和拒绝策略。（当任务队列满了之后，新来的任务就会被线程池自行拒绝）
         * 3、创建可缓存线程的线程池
         * 4、创建支持周期性执行任务的线程池
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        // 创建 jdk1.8 的forkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);

        /**
         * 线程工厂创建   一般默认即可
         * 1、默认线程工厂
         * 2、privileged 线程工厂 可以配置
         */
        //默认线程工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //可以配置线程的操作访问权限
        ThreadFactory privilegedThreadFactory = Executors.privilegedThreadFactory();
        //使用匿名内部类创建线程工厂
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("thread");
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }
        };

        //使用线程工厂创建线程池
        //ExecutorService executorService2 = Executors.newCachedThreadPool(threadFactory);


        /**
         * 阻塞队列
         * 1、ArrayBlockingQueue:列表形式的工作队列，必须有初始列大小，有界队列，先进先出
         * 2、LinkedBlockingQueue：链表形式的工作队列，可以选择设置初始队列的大小，有界/无界队列，先进先出
         * 3、SynchronousQueue：不是一个真正的队列，而是一种在线程之间移交的机制。要将一个元素放入到 SynchronousQueue 中
         *      必须有另一个线程正在等待接受只给元素，如果没有线程等待，并且线程池的当前大小效于最大值，那么
         *      ThreadPoolExecutor 将创建一个线程，否则根据包和策略，这个任务将被拒绝，使用直接移交将更高效，
         *      因为任务会直接移交给执行它的线程，而不是被首先放在队列中，然后由工作者线程从队列中提取任务，
         *      只有当线程池是误解的或者可以拒绝任务时，SynchronousQueue 才有实际价值
         * 4、PriorityBlockingQueue：优先级队列，无界队列，根据优先级安排任务，任务优先级是通过自然顺序或Comparator接口
         *      来定义的
         */
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5,true);
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(1);
        SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>(true);
        PriorityBlockingQueue<Runnable> priorityBlockingQueue = new PriorityBlockingQueue<>(5);
        PriorityBlockingQueue<Callable> priorityBlockingQueue2 = new PriorityBlockingQueue<>();

        //ScheduledThreadPoolExecutor scheduledThreadPoolExecutor1 = new ScheduledThreadPoolExecutor(5);
        //DelayedWorkQueue<> delayedWorkQueue = new DelayedWorkQueue<>();
        ///DelayedWorkQueue runnableDelayedWorkQueue = new DelayedWorkQueue();
        //DelayQueue<Callable> delayeds = new DelayQueue<Delayed>();

        /**
         * 创建线程池的方法，默认队列为 DelayedWorkQueue
         * 可以理解是手动创建线程池的升级方法，只需要设置核心线程数，其他的参数都默认设置好了
         */
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPoolExecutor.execute(()->{

        });



        /**
         * 1、为什么要通过 ThreadPoolExecutor 手动创建线程池？
         *      根据阿里编码公约：线程池不允许使用Executors去创建，而是需要通过手动的方式去创建，
         *      这样处理方式可以规避资源耗尽的风险。因为用Executors创建，有些默认的参数较大，可都会导致OOM。
         *
         * 2、核心参数：
         *      核心线程数、最大线程数、线程空闲时候存活时间、时间单位、工作队列、线程工厂、拒绝策略
         *
         * 3、拒绝策略：
         *  AbortPolicy：直接抛出异常
         *  DiscardPolicy：	丢弃当前被拒绝的任务(不抛出异常)
         *  DiscardOldestPolicy： 将工作队列中最早的任务丢弃，去执行新的任务
         *  CallerRunsPolicy：	交给调用线程池的线程处理
         *
         * 4、以下案例可以很好的说明线程池的工作原理
         *
         */
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(1);

        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, queue,threadFactory,new ThreadPoolExecutor.AbortPolicy());
        //设置方式
        //threadPool.setThreadFactory(Executors.privilegedThreadFactory());
        //threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());


        /**
         * 线程池中活跃的线程数： 1
         * 线程池中活跃的线程数： 2
         * 线程池中活跃的线程数： 2
         * ----------------队列中阻塞的线程数1
         * 线程池中活跃的线程数： 3
         * ----------------队列中阻塞的线程数1
         * 线程池中活跃的线程数： 4
         * ----------------队列中阻塞的线程数1
         * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task Thread[Thread5,5,main]
         * rejected from java.util.concurrent.ThreadPoolExecutor@15aeb7ab
         * [Running, pool size = 4, active threads = 4, queued tasks = 1, completed tasks = 0]
         * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(Unknown Source)
         * 	at java.util.concurrent.ThreadPoolExecutor.reject(Unknown Source)
         * 	at java.util.concurrent.ThreadPoolExecutor.execute(Unknown Source)
         * 	at com.juc.juc04_threadPool.ThreadPoolDemo.main(ThreadPoolDemo.java:125)
         */

        for (int i = 0; i < 500 ; i++) {
                     threadPool.execute(
                             new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
                     System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
                     if (queue.size() > 0) {
                         System.out.println("----------------队列中阻塞的线程数" + queue.size());
                     }
                 }
                 threadPool.shutdown();
    }
}
