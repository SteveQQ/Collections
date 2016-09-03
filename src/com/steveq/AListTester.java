package com.steveq;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AListTester {
    public static void main(String[] args){
        AList<Integer> testList = new AList<>();
        AList<Integer> testList2 = new AList<>(0);
        AList<Integer> testList3 = new AList<>(2);
        if (testList3.size() == 2) {
            System.out.println("size is equal 2 - PASSED!");
        }
        //assert (testList3.size() == 3) : "size is equals" + testList3.size() + " PASSED!";
        AList<Integer> testList4;
        try {
            testList4 = new AList<>(-1);
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage() + " -PASSED!");
        }
    }
}
