package com.steveq;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by SteveQ on 2017-01-21.
 */
public class BSTTester {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        System.out.println(bst.add(10));
        System.out.println(bst.add(8));
        System.out.println(bst.add(12));
        System.out.println(bst.add(24));
        System.out.println(bst.add(18));
        System.out.println(bst.add(27));
        System.out.println(bst.add(5));
        System.out.println(bst);

    }
}
