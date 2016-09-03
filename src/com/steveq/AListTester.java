package com.steveq;

import java.util.ArrayList;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AListTester {
    public static void main(String[] args){
        AList<Integer> testList = new AList<>();
        System.out.println(testList.size());
        System.out.println(testList);
        testList.add(1);
        testList.add(2);
        testList.add(4);
        System.out.println(testList);
        ArrayList<String> ar = new ArrayList<>();
        ar.add("a");
        ar.add("b");
        ar.add("c");
        ar.add("d");
        ar.add("e");
        ar.add("f");
        System.out.println(ar);
        AList<String> al = new AList<>(3);
        System.out.println(al.size());
        al.add("x");
        al.add("y");
        al.add("z");
        System.out.println(al);
        AList<String> als = new AList<>(ar);
        System.out.println(als);
        als.remove(5);
        System.out.println(als);
        System.out.println(als.dataArray.length);
        System.out.println(als.contains("z"));
        als.remove("c");
        System.out.println(als);
        als.clear();
        System.out.println(als);




    }
}
