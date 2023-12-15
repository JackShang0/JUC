package com.jvm.chapter13;

/**
 * new String("ab") 会创建几个对象？ 
 * 看字节码就知道是2个对象
 */
public class StringNewTest {
    public static void main(String[] args) {
        String str1 = new String("ab");

        String str2 = new String("a") + new String("b");
    }
}
