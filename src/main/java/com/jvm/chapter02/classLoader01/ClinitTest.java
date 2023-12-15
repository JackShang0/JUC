package com.jvm.chapter02.classLoader01;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class ClinitTest {

    //任何一个类声明以后  内部至少有一个构造器
    private int a = 1;
    private static int c = 3;


    public static void main(String[] args) {
        int b = 2;
    }

    public ClinitTest(){
        a = 10;
        int d = 20;
    }
}
