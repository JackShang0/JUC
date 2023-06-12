package com.juc.shop.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/13
 * @version: 1.0
 */
public class demo {
    public static void main(String[] args) {

        /**
         * 链式编程
         */
        Student student = new Student();
        //传统赋值方式
        student.setAge(1);
        student.setId(1);
        student.setName("zs");
        System.out.println("student = " + student);

        //链式编程设值    实体类加注解 @Accessors(chain = true)
        Student ls = new Student().setName("ls").setId(2).setAge(2);
        System.out.println("ls = " + ls);

        /**
         * get 和 join 区别
         * 使用上功能没有区别，但是get编译时会报错，需要抛出异常，但是join不会编译时报错，不需要爆出异常
         */
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello world!");
        System.out.println(stringCompletableFuture.join());

    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
class Student {
    private Integer id;
    private String name;
    private Integer age;
}
