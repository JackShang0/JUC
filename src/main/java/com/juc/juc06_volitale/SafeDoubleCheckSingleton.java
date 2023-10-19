package com.juc.juc06_volitale;

/**
 * @description: 双重检测锁定/双端检测锁定   不加volatile会导致空指针问题，某个线程会获取到一个未实例化的对象
 * @author: shangqj
 * @date: 2023/8/7
 * @version: 1.0
 */
public class SafeDoubleCheckSingleton {

    private static volatile SafeDoubleCheckSingleton singleton;

    //私有构造方法
    private SafeDoubleCheckSingleton(){}


    /**
     * 解决问题：
     * 1、多线程情况下，普通单例模式没有办法保证 单例     ->  保证单例
     * 2、可能会导致未初始化对象而空指针                ->  volatile 解决指令重排
     * @return
     */
    public static SafeDoubleCheckSingleton getSingleton(){
        if (singleton == null){
            //1、多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class){
                if (singleton == null){
                    //隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    //创建对象过程：  1、先申请空间  2、然后再把new的实例对象放到空间中  3、设置instance 指向内存地址

                    //正常顺序是123，多线程，指令重排序可能会导致的顺序是132，就会导致空指针问题
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        //2、创建对象完毕，执行getInstance 将不需要获取锁，直接返回创建对象
        return singleton;
    }
}
