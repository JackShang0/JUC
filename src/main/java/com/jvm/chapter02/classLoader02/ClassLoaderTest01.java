package com.jvm.chapter02.classLoader02;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class ClassLoaderTest01 {
    public static void main(String[] args) {
        //获取系统类/应用程序类加载器
        java.lang.ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);         //sun.misc.Launcher$AppClassLoader@18b4aac2


        //根据加载器获取其上层：扩展类加载器
        java.lang.ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);            //sun.misc.Launcher$ExtClassLoader@497470ed


        //根据扩展类加载器，获取其上层
        java.lang.ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);      //null

        //对于用户自定义类来讲:默认使用的系统类加载器加载
        java.lang.ClassLoader classLoader = ClassLoaderTest01.class.getClassLoader();
        System.out.println(classLoader);               //sun.misc.Launcher$AppClassLoader@18b4aac2


        //String 类:加载不到，则说明是用引导类加载器加载的  --->  系统的核心类库都是使用引导类加载器加载的
        java.lang.ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);              //null
    }
}
