package com.jvm.chapter05;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/3
 * @version: 1.0
 */
public class StackTest {
    public static void main(String[] args) {

        StackTest stackTest = new StackTest();
        stackTest.methodA();
    }

    public void methodA(){
        int a = 10;
        int b = 20;

        methodB();
    }

    public void methodB(){
        int c = 30;
        int d = 40;
    }
}
