package com.jvm.chapter05;

/**
 * @description: 动态链接 （指向运行时常量池的方法引用）
 * @author: shangqj
 * @date: 2023/11/7
 * @version: 1.0
 */
public class DynamicLinkingTest {
    public static void main(String[] args) {

    }

    int num = 1;

    public  void methodA() {
        System.out.println("method A ...");
    }

    public  void methodB() {
        System.out.println("method B ...");

        methodA();

        num ++;
    }
}
