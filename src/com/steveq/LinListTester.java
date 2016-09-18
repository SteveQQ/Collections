package com.steveq;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by SteveQ on 2016-09-14.
 */
public class LinListTester {
    public static void main(String[] args){
        LinList<String> lList = new LinList<>();
        lList.addFirst("a");
        System.out.println(lList);
        lList.addFirst("b");
        System.out.println(lList);
        lList.addFirst("c");
        System.out.println(lList);
        lList.add(2, "d");
        System.out.println(lList);
        lList.add(4, "e");
        System.out.println(lList);
        lList.addLast("f");
        System.out.println(lList);
        ListIterator<String> it = lList.listIterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(lList);
        System.out.println(lList.removeLast());
        System.out.println(lList);
        System.out.println(lList.removeLast());
        System.out.println(lList);
        System.out.println(lList.remove("d"));
        System.out.println(lList);
        System.out.println(lList.remove("a"));
        System.out.println(lList);
        System.out.println(lList.remove("b"));
        System.out.println(lList);
    }
}
