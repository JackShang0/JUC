package com.jvm.chapter13;

public class StringIntern1 {

    public static void main(String[] args) {

        String s3 = new String("1") + new String("1");
        String intern = s3.intern();
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(intern == s4);
    }

}
