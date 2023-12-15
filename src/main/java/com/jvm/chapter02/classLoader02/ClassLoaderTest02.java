package com.jvm.chapter02.classLoader02;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;
import java.net.URL;
import java.security.Provider;


/**
 * @description: 引导类加载器  + 自定义加载器
 * @author: shangqj
 * @date: 2023/11/2
 * @version: 1.0
 */
public class ClassLoaderTest02 {
    public static void main(String[] args) {
        System.out.println("******启动类加载器******");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());

            //输出结果
            //file:/D:/JAVAJDK/Java/jdk8/lib/resources.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/rt.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/sunrsasign.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/jsse.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/jce.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/charsets.jar
            //file:/D:/JAVAJDK/Java/jdk8/lib/jfr.jar
            //file:/D:/JAVAJDK/Java/jdk8/classes
        }

        //找到上面jkk8下面的随便一个包，然后解压，找到随便一个文件，例如：jsse下的provider
        ClassLoader classLoader = Provider.class.getClassLoader();
        //输出为null  则是我们的引导类加载器加载的
        System.out.println(classLoader);


        System.out.println("******启动类加载器******");
        String property = System.getProperty("java.ext.dirs");
        for (String path : property.split(";")) {
            System.out.println(path);
        }
        //类加载器输出加载路径：
        // D:\JAVAJDK\Java\jdk8\lib\ext
        //C:\WINDOWS\Sun\Java\lib\ext


        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        //输出结果为：sun.misc.Launcher$ExtClassLoader@621be5d1   是扩展类加载器
        System.out.println(classLoader1);
    }
}
