package com.juc.juc02_lock.lock02_show;

import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/15
 * @version: 1.0
 */
public class PhoneShowByLock {

    /**
     * 1、标准访问 ab 两个线程，请问先打印邮件还是短信                       ->先打印邮件再打印短信
     * 2、sendEmail 方法中暂停3秒种，请问先打印邮件还是短信                 ->先打印邮件再打印短信
     * 3、添加一个普通 hello 方法，先打印邮件还是 hello
     * 4、有两部手机，请问先打印 短信还是邮件                                 ->先打印短信再打印邮件
     * 5、有两个静态同步方法，有一部手机，请问是先打印邮件还是短信               ->先打印邮件再打印短信
     * 6、两个静态同步方法，有两部手机，请问是先打印邮件还是短信                ->先打印邮件再打印短信
     * 7、一个静态同步方法，一个普通同步方法，有一部手机，请问先打印邮件还是短信     ->先打印短信再打印邮件
     * 8、一个静态同步方法，一个普通同步方法，有两部手机，请问先打印邮件还是短信     ->先打印短信再打印邮件
     *
     * 总结：
     *      synchronized 如果加到了对象下的某个方法上  相当于是个对象锁
     *      synchronized 如果加到了静态方法上，相当于是类锁 整个对象的class被锁
     *      普通无锁hello方法、加锁sms方法、加锁静态email方法 三者之间其实是不同的锁，
     *      相同级别的方法如果涉及争抢使用，则需要等当前的锁被释放后，才可以使用
     * 
     * @param args
     */
    public static void main(String[] args) {

        //lock01();
        //lock02();
        //lock03();
        lock04();

    }

    /**
     * 场景1-2：->先打印邮件再打印短信
     * 两个方法同时都加了锁，资源类中email方法执行的时候，其他线程是没有办法进入资源类的，
     * 所以sms方法只有等email方法执行完成之后，（即使email方法睡了3秒种）才能进入资源类调用sms方法
     * 所以先打印邮件在打印短信
     * 结论：
     * 1-2
     * 一个对象里面如果有多个 synchronized 方法，某一时刻内，只要一个线程去调用其中一个synchronized方法了
     * 其他的线程都只能等待，换句话说，某一时刻，只能由唯一的一个线程去访问 synchronized 方法
     * 锁的是当前对象的 this，被锁定之后，其他的线程都不能进入到当前对象的其他的synchronized方法
     * 一个类中的一个方法如果上了 synchronized 方法修饰，则锁的不是这个方法，而是整个类
     */
    public static void lock01(){

        Phone phone = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{phone.sendSms();},"b").start();

    }

    /**
     * 场景3：hello方法上面没有锁，所以email方法与hello方法之间并不会产生场景1-2之间的资源类被锁，另一线程无法进入的问题
     * 场景4：因为是完全不同的两个对象，所以资源之间不存在争抢的问题，谁在前面执行谁
     *  3-4
     *   加个普通方法后发现和同步锁无关
     *   换成两个对象后，不是同一把锁了，不存在锁的占用情况
     */
    public static void lock02(){

        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //场景3
        //new Thread(()->{phone.hello();},"b").start();
        //场景4
        new Thread(()->{phone2.sendSms();},"b").start();

    }

    /**
     * 场景5-6：执行结果都是先打印 email 后打印 sms
     * 对于静态的方法，我们锁的是整个对象，相当于是类锁，而不是针对实例对象锁
     *
     * 三种 synchronized 锁的内容有一些差异：
     * 对于普通同步方法，锁的是当前实例对象，通常指this，具体的一部手机，所有的普通同步方法用的都是一把锁 -> 实例对象本身
     * 对于静态同步方法，锁的是当前类class对象，如 Phone.class 唯一的一个模板
     * 对于同步方法块，锁的是 synchronized 括号内的对象
     */
    public static void lock03(){

        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{phone2.sendSms();},"b").start();

    }


    /**
     * 场景7：email方法上有加锁，相当于是加的类锁，sms方法锁的是当前的实例对象，
     *          两个为不同锁，不存在争抢占用，所以谁快谁先打印结果
     * 场景8：与7类似，锁不同，使用不存在影响
     *
     *  当一个线程试图访问同步代码块时，它首先必须得到锁，正常退出或抛出异常时必须释放锁
     *  所有的普通同步方法用到的的都是同一把锁——实例对象本身，就是new出来的具体实例对象本身
     *  也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放后才能获取锁
     *
     *  所有的静态同步方法用到的也是同一把锁——类对象本身，就是我们说过的唯一的模板class
     *  具体实例对象和唯一模板class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞争的
     *  但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁
     *
     */
    public static void lock04(){

        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        },"a").start();


        new Thread(()->{phone2.sendSms();},"b").start();

    }
}
