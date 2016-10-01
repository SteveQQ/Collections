package com.steveq;


import java.util.AbstractMap;

/**
 * Created by SteveQ on 2016-10-01.
 */
public class HMapTester{

    public static void main(String[] args){
        HMap<Integer, String> hm = new HMap<>();
        System.out.println(hm.isEmpty());
        System.out.println(hm.mEntries.length);
        System.out.println(hm.mEntries);
        System.out.println(hm.entrySet());
        System.out.println(hm.put(1, "a"));
        System.out.println(hm.entrySet());
        System.out.println(hm.put(10, "gh"));
        System.out.println(hm.entrySet());
        System.out.println(hm.entrySet().size());
        System.out.println(hm.get(10));
        //System.out.println(hm.keySet());
    }

}
