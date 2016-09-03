package com.steveq;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AListTester {
    public static void main(String[] args){
        AList<Integer> testList = new AList<>();
        System.out.println(testList.size());
        System.out.println(testList.dataArray.length);
        System.out.println(testList.isPlaceForNext());
        System.out.println(testList);
        testList.stretchArray();
        System.out.println(testList);
        System.out.println(testList.dataArray.length);


    }
}
