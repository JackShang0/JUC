package com.jvm.chapter02.classLoader02;


/**
 * @description: ClassLoader的获取方式
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class ClassLoaderTest03 {

    public static void main(String[] args) {
        try {
            //1.获取当前类的 classLoader
            ClassLoader classLoader =  Class.forName("java.lang.String").getClassLoader();
            //String是引导类，获取不到类加载器
            System.out.println(classLoader);

            //2.获取当前线程上下文的ClassLoader
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(contextClassLoader);

            //3.获取当前系统的 ClassLoader
            ClassLoader classLoader1 = ClassLoader.getSystemClassLoader().getParent();
            System.out.println(classLoader1);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
