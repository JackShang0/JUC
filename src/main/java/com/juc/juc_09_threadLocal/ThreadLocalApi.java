package com.juc.juc_09_threadLocal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/9/4
 * @version: 1.0
 */
public class ThreadLocalApi {
    /**
     * 案例一： 5个销售卖房子,统计总售房数量
     * 案例二：在以上的基础上，需要统计每个销售的售房以及提成
     *
     * @param args a
     */
    public static void main(String[] args) {
        House house = new House();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int number = new Random().nextInt(5) + 1;
                //System.out.println(number);
                try {
                    for (int j = 1; j <= number; j++) {
                        house.saleHouse();
                        house.saleByThreadVolume();
                    }

                    System.out.println(Thread.currentThread().getName() + "号 \t" + "售卖出  "
                            +number+"  套房，提成   "+house.saleVolume.get()*10000);
                }finally {
                    //使用完成之后要清空
                    house.saleVolume.remove();
                }

            }, String.valueOf(i)).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "  sale   " + house.saleNumber);

    }
}


class House {
    int saleNumber = 0;
    String name ;

    public synchronized void saleHouse() {
        ++saleNumber;
    }

    /*ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/


    //使用java8的匿名内部类写  等同于上面的初始化方法 👆
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleByThreadVolume() {
        saleVolume.set(1 + saleVolume.get());
    }
}

