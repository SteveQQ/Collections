package com.steveq;


import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.Collection;

/**
 * Created by SteveQ on 2016-09-14.
 */
public class LinList<E> extends AbstractSequentialList<E>{

    private Node firstNode;

    //-----CONSTRUCTORS-----//
    public LinList(){
        firstNode = null;
    }
    //-----CONSTRUCTORS-----//

    //-----METHODS-----//
    //-----METHODS-----//

    //-----INNER CLASSES-----//
    public class Node{
        private Node nextNode;
        private Node prevNode;
        private E mdata;
    }

    //-----INNER CLASSES-----//
}
