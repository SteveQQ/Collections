package com.steveq;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by SteveQ on 2016-09-14.
 */
public class LinListTester {
    public static void main(String[] args){
        LinList<String> lList = new LinList<>();
        lList.addFirst("d");
        lList.addFirst("c");
        lList.addFirst("b");
        lList.addFirst("a");
        ListIterator<String> iterator = lList.listIterator(2);
        System.out.println(lList);
        System.out.println(lList.size());
        System.out.println(iterator.previous());
        iterator.remove();
        System.out.println(lList);
        System.out.println(lList.size());
        iterator.remove();
        System.out.println(lList);
        System.out.println(lList.size());
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(lList);
        System.out.println(iterator.next());
        System.out.println(lList.size());
        iterator.remove();
        System.out.println(lList);
        System.out.println(lList.size());
        System.out.println(iterator.previous());
        iterator.remove();
        System.out.println(lList);
        System.out.println(lList.size());
    }
}
