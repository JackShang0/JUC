package com.juc.juc02_lock.lock03_sychronized;


import com.juc.juc02_lock.lock02_show.Phone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description
 * @author: shangqj
 * @date: 2023/6/15
 * @version: 1.0
 */
public class SynchronizedDemo {

    public void m1(){
        Object o = new Object();
        Phone phone = new Phone();
        synchronized (o){
            System.out.println("--hello synchronized code block");
        }
    }

    public synchronized void m2(){
        System.out.println("--hello synchronized ");
    }

    public static synchronized void m3(){
        System.out.println("--hello static synchronized ");
    }

    public static void main(String[] args) {

        HashSet<String> objects2 = new HashSet<>();
        List<String> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("2");

        objects2.addAll(objects);
        System.out.println("objects2.size() = " + objects2.size());


    }
}
