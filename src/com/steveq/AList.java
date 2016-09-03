package com.steveq;

import java.util.AbstractList;
import java.util.Collection;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AList<E> extends AbstractList<E> implements Collection<E>{
    //Other possibilities to declare array:
    //Object[] DEFAULT_SIZE = new Object[]{}; ----> [] - 0 length array
    //Object[] DEFAULT_SIZE = new Object[]{2}; ----> [2] - array with initial value
    //Object[] DEFAULT_SIZE = new Object[3]; ----> [null, null, null] - array with initial size
    private static final Object[] DEFAULT_SIZE = {};
    private Object[] dataArray;

    public AList(Integer size) throws IllegalArgumentException{
        if(size == 0){
            dataArray = DEFAULT_SIZE;
        } else if(size > 0){
            dataArray = new Object[size];
        } else {
            throw new IllegalArgumentException("Size cannot be less than zero");
        }
    }

    public AList(){
        dataArray = DEFAULT_SIZE;
    }

    @Override
    public E get(int index){
        return (E)dataArray[index];
    }

    @Override
    public int size(){
        return dataArray.length;
    }

}
