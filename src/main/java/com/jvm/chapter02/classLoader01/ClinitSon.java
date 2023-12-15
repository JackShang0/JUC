package com.jvm.chapter02.classLoader01;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class ClinitSon {

    static class Father{
        public static int A = 1;
        static{
            A = 2;
        }
    }

    static class Son extends Father{
        public static int B = A;
    }

    public static void main(String[] args) {
        //加载Father类，其次加载Son类。
        System.out.println(Son.B); //2
    }

}
