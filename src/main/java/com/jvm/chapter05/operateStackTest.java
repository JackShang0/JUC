package com.jvm.chapter05;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/6
 * @version: 1.0
 */
public class operateStackTest {

    public void testAddOperateStack(){
        byte i = 15;
        int j = 8;
        int k = i + j;
    }

    public static void main(String[] args) {

    }

    //程序员面试过程中，常见的 i++ 和 ++i 的区别，放到字节码篇章时再介绍。
    public void add(){
        //第1类问题：
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        //第2类问题：
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        //第3类问题：
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        //第4类问题：
        int i9 = 10;
        int i10 = i9++ + ++i9;
    }

}
