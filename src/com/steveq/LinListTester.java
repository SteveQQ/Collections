package com.steveq;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by SteveQ on 2016-09-14.
 */
public class LinListTester {
    public static void main(String[] args){
        LinList<String> lList = new LinList<>();
        lList.addFirst("c");
        lList.addFirst("b");
        lList.addFirst("a");
        System.out.println(lList.size());
        ListIterator<String> iterator = lList.listIterator();
        System.out.println(lList);
        System.out.println(iterator.nextIndex());
        iterator.add("x");
        System.out.println(lList);
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());
        System.out.println(iterator.next());
        iterator.set("c");
        System.out.println(lList);
        System.out.println(iterator.next());
        iterator.set("c");
        System.out.println(lList);
        System.out.println(iterator.previous());
        iterator.set("d");
        System.out.println(lList);
        System.out.println(iterator.previous());
        iterator.set("d");
        System.out.println(lList);
    }
}
