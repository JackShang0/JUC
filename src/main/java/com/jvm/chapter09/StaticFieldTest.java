package com.jvm.chapter09;

/**
 * 结论：
 *  静态变量在jdk6/7存在与永久代中，在jdk8存在于堆中 //private static byte[] arr
 *  静态引用对应的对象实体始终都存在堆空间 //new byte[1024 * 1024 * 100];
 *
 * jdk7：
 * -Xms200m -Xmx200m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails
 * jdk 8：
 * -Xms200m -Xmx200m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 */
public class StaticFieldTest {
    private static byte[] arr = new byte[1024 * 1024 * 100]; //100MB

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);
    }
}
