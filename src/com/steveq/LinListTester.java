package com.steveq;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by SteveQ on 2016-09-14.
 */
public class LinListTester {
    public static void main(String[] args){
        ArrayList<String> tar = new ArrayList<>();
        tar.add("a");
        tar.add("b");
        tar.add("c");
        System.out.println(tar);
        LinList<String> lList = new LinList<>();
        lList.addAll(tar);
        System.out.println(lList);
    }
}
