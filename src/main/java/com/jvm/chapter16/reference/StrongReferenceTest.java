package com.jvm.chapter16.reference;

/**
 * 强引用的测试
 */
public class StrongReferenceTest {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("Hello,阿里巴巴");
        StringBuffer str1 = str;

        str = null;
        System.gc(); //调用垃圾回收

        try {
            Thread.sleep(3000); //设置3秒钟延迟保证GC垃圾回收能够实现
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(str1);
    }
}
