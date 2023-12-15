package com.jvm.chapter05;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/6
 * @version: 1.0
 */
public class StackFrameTest {
    public static void main(String[] args) {
        StackFrameTest stackFrameTest = new StackFrameTest();
        stackFrameTest.method01();

    }

    public void method01(){
        System.out.println("method 01 开始执行");
        method02();
        System.out.println("method 01 执行结束");
    }

    public int method02(){
        System.out.println("method 02 开始执行");
        int i = 20;
        int v =(int) method03();
        System.out.println("method 02 即将结束");
        return i+v;
    }

    public double method03() {
        System.out.println("method 03 开始执行");
        double j = 30.0;
        System.out.println("method 03 即将结束");
        return j;
    }
}
