package com.steveq;


import java.util.AbstractMap;

/**
 * Created by SteveQ on 2016-10-01.
 */
public class HMapTester{

    public static void main(String[] args){
        HMap<Integer, String> hm = new HMap<>();
        System.out.println(hm.put(1, "a"));
        System.out.println(hm.put(2, "b"));
        System.out.println(hm.put(3, "c"));
        System.out.println(hm.put(4, "d"));
        System.out.println(hm.entrySet());
        System.out.println(hm.hashtable.length);
        System.out.println(hm.get(4));
    }

}
