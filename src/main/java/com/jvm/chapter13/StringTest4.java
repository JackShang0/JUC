package com.jvm.chapter13;

public class StringTest4 {

    public static void main(String[] args) {
        System.out.println();//1230
        System.out.println("1");//1231
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");//1240
        //如下的字符串"1" 到 "10"不会再次加载
        System.out.println("1");//1241
        System.out.println("2");//1241
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");//1241
    }


    public void test1(){
        String s1 = "a" + "b" + "c"; //编译期优化：等同于"abc"
        String s2 = "abc"; //"abc"一定是放在字符串常量池中，将此地址赋给s2
        /*
         * 最终.java编译成.class,再执行.class
         * String s1 = "abc";
         * String s2 = "abc"
         */
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2)); //true
    }


}
