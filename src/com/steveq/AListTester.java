package com.steveq;

import java.util.ArrayList;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AListTester {
    public static void main(String[] args){
        AList<Integer> testList = new AList<>();
        System.out.println(testList.size());
        System.out.println(testList.dataArray.length);
        System.out.println(testList);
        System.out.println(testList);
        System.out.println(testList.dataArray.length);
        testList.add(1);
        testList.add(2);
        testList.add(4);
        System.out.println(testList);
        ArrayList<String> ar = new ArrayList<>();
        ar.add("a");
        ar.add("b");
        ar.add("c");
        System.out.println(ar);
        AList<String> al = new AList<>(3);
        System.out.println(al.size());
        System.out.println(al.dataArray.length);
        al.add("x");
        al.add("y");
        al.add("z");
        System.out.println(al);
        AList<String> als = new AList<>(ar);
        System.out.println(als);





    }
}
