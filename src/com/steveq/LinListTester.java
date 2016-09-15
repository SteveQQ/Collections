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
        System.out.println(lList);
        ListIterator<String> lit = lList.listIterator(2);
        System.out.println(lit.previous());
        System.out.println(lit.next());
        System.out.println(lit.previous());
        System.out.println(lit.previous());
        System.out.println(lit.next());
        System.out.println(lit.next());
        System.out.println(lit.next());
        System.out.println(lit.next());

    }
}
