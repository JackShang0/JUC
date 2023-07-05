package com.juc.juc01_thread.thread04_shop.project.bean;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/18
 * @version: 1.0
 */
@NoArgsConstructor
@Data
public class Bean_NetMall {




    static List<Bean_NetMall> list = Arrays.asList(
            new Bean_NetMall("JD"),
            new Bean_NetMall("DD"),
            new Bean_NetMall("TB"),
            new Bean_NetMall("PDD"),
            new Bean_NetMall("DY"));


    private String netMallName;

    public Bean_NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    private double price;



    public double price(String netMallName)  {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble(100);
    }


    /**
     * 一个个去查询书籍价格   cost time --3062
     *
     * @param args
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        //一个个查询
        List<String> m = getPrice0(list, "mysql");
        long endTime = System.currentTimeMillis();
        System.out.println("cost time1 --"+(endTime-startTime));
        //线程优化之后
        long startTime2 = System.currentTimeMillis();
        List<String> m2 = getPriceByCompletableFuture(list, "mysql");
        long endTime2 = System.currentTimeMillis();
        System.out.println("cost time2 --"+(endTime2-startTime2));
        for (String s : m) {
            System.out.println(s);
        }

        for (String s : m2) {
            System.out.println(s);
        }




    }


    /**
     *  一个个去查询数据价格 做比价
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice0(List<Bean_NetMall> list,String productName){


        List<String> collect = list.stream()
                .map((netMall) -> String.format(productName + " in %s price is "+ThreadLocalRandom.current().nextDouble(90,100),
                        netMall.getNetMallName(),
                        netMall.price("mysql"))).collect(Collectors.toList());
        return collect;
    }

    /**
     * 使用多线程优化比较需求
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<Bean_NetMall> list,String productName){

        /*//1、首先得到  CompletableFuture<String> 类型的结果     ->      然后转换成List<String>
        List<CompletableFuture<String>> mysql = list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is " + ThreadLocalRandom.current().nextDouble(90, 100),
                netMall.getNetMallName(),
                netMall.price("mysql")))
                )
                .collect(Collectors.toList());

        //2、得到Stream<String>流       ->      然后转换成List<String>
        Stream<String> stringStream = mysql.stream().map(CompletableFuture::join);

        //3、转换 List<String>
        List<String> collect = stringStream.collect(Collectors.toList());*/

        List<String> stringList = list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(
                        () -> String.format(productName + " in %s price is " + ThreadLocalRandom.current().nextDouble(90, 100),
                netMall.getNetMallName(),
                netMall.price("mysql"))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());

        return stringList;


        //基本操作逻辑
        /*CompletableFuture<Bean_NetMall> future = CompletableFuture.
                supplyAsync((Supplier<Bean_NetMall>) () -> null).
                whenComplete((s, throwable) -> {
                }).
                exceptionally(throwable -> null);

        Bean_NetMall join = future.join();

        return null ;*/
    }


}
