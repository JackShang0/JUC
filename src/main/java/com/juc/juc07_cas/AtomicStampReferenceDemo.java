package com.juc.juc07_cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:
 * @author: shangqj
 * @date: 2023/8/15
 * @version: 1.0
 */
public class AtomicStampReferenceDemo {
    public static void main(String[] args) {
        Book book = new Book(1,"mysql");
        AtomicStampedReference<Book> bookAtomicStampedReference = new AtomicStampedReference<Book>(book,1);
        System.out.println(bookAtomicStampedReference.getReference()+"\t"+bookAtomicStampedReference.getStamp());

        Book java = new Book(2,"java");

        boolean b ;
        b = bookAtomicStampedReference.compareAndSet(
                book,java,bookAtomicStampedReference.getStamp(),bookAtomicStampedReference.getStamp()+1);

        System.out.println(b+"\t"+bookAtomicStampedReference.getReference()+"\t"+bookAtomicStampedReference.getStamp());


        b = bookAtomicStampedReference.compareAndSet(
                java,book,bookAtomicStampedReference.getStamp(),bookAtomicStampedReference.getStamp()+1);

        System.out.println(b+"\t"+bookAtomicStampedReference.getReference()+"\t"+bookAtomicStampedReference.getStamp());

    }
}


@Data
@AllArgsConstructor
class Book{
    int id;
    String name ;
}