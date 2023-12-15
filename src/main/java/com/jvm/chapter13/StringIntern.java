package com.jvm.chapter13;

public class StringIntern {

    public static void main(String[] args) {

        /**
         * ① String s = new String("1")
         * 创建了两个对象
         * 		堆空间中一个new对象
         * 		字符串常量池中一个字符串常量"1"（注意：此时字符串常量池中已有"1"）
         * ② s.intern()由于字符串常量池中已存在"1"
         *
         * s  指向的是堆空间中的对象地址
         * s2 指向的是堆空间中常量池中"1"的地址
         * 所以不相等
         */
        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false   jdk7/8：false

        /**
         * ① String s3 = new String("1") + new String("1")
         * 等价于new String（"11"），但是，常量池中并不生成字符串"11"；
         * ② s3.intern()
         * 由于此时常量池中并无"11"，所以把s3中记录的对象的地址存入常量池
         * 所以s3 和 s4 指向的都是一个地址
         */
        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        s3.intern();//在字符串常量池中生成"11"。如何理解：jdk6:在常量池中真正创建了一个新的对象"11",也就有新的地址。
                                            //         jdk7:此时常量池中并没有真正创建"11",而是创建一个指向堆空间中new String("11")的地址
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"11"的地址
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }

}
