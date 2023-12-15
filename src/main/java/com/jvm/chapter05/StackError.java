package com.jvm.chapter05;

/**
 * @description: 演示栈中的异常
 * @author: shangqj
 * @date: 2023/11/6
 * @version: 1.0
 */
public class StackError {

    private static int count = 1;
    public static void main(String[] args) {
        //默认情况下数值是 11409
        //-Xss256k设置栈大小为256k，默认为1025k  打印数值为 2475
        System.out.println(count);
        count ++;
        main(args);
    }
}
