package com.test10099;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description
 * @author: shangqj
 * @date: 2023/4/21
 * @version: 1.0
 */
public class test01 {
    public static void main(String[] args) {

        String a ="1.0.0.0/18,2.0.0.0/18,3.0.0.0/18";
        String b ="5.0.0.0/18,2.0.0.0/18,3.0.0.0/18";
        String[] splitA = a.split(",");
        String[] splitB = b.split(",");
        List<String> stringListA = Arrays.asList(splitA);
        List<String> stringListB = Arrays.asList(splitB);

        List<String> collectA = Stream.of(splitA).collect(Collectors.toList());
        List<String> collectB = Stream.of(splitB).collect(Collectors.toList());

        boolean b1 = collectA.removeAll(collectB);
        //boolean b2 = stringListA.removeAll(stringListB);
        System.out.println("b1 = " + b1);
        //System.out.println("b2 = " + b2);


        ArrayList<String> stringsA = new ArrayList<>();
        stringsA.add("a");
        stringsA.add("b");
        //stringsA.add("c");
        ArrayList<String> stringsB = new ArrayList<>();
        stringsB.add("a");
        stringsB.add("d");
        stringsB.add("e");

        /*for (int i = 0; i < stringsA.size(); i++) {
            System.out.println("i = " + i+"-----"+stringsA.get(i));

            for (int i1 = 0; i1 < stringsB.size(); i1++) {
                System.out.println("i1 = " + i1+"-----"+stringsB.get(i1));

                if (stringsA.get(i).equals(stringsB.get(i1))){
                    stringsA.remove(i);
                    //stringsA.remove(stringsB.get(i1));
                }
            }
        }*/


        boolean B = stringsA.removeAll(stringsB);
        System.out.println("B = " + B);


        System.out.println("stringsA = " + stringsA);

        /*for (String s : stringsA) {
            System.out.println("s = " + s);
            for (String s1 : stringsB) {
                System.out.println("s1 = " + s1);
                if (s1.equals(s)){
                    stringsB.remove(s);
                    System.out.println("stringsB = " + stringsB);
                }
            }
        }*/


        /*SimpleDateFormat sdfIntegral = new SimpleDateFormat("yyyy-MM-dd-HH");
        System.out.println(sdfIntegral.format(new Date()));*/



    }

    @Test
    public void way(){

        Integer num ;
        Integer x ;
        Integer y ;

        num = 70000;
        if (num>65535){
            x = num / 65535;
            y = num - 65535;
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println(x+"."+y);
        }

    }

}
