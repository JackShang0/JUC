package com.juc.juc07_cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/15
 * @version: 1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User zs = new User(3, "zs");
        User ls = new User(4, "ls");

        atomicReference.set(zs);

        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls) + "\t" + atomicReference.get().toString());

    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    int age;
    String name;

}
