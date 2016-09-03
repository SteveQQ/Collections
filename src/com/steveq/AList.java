package com.steveq;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AList<E> extends AbstractList<E> implements Collection<E>{
    //Other possibilities to declare array:
    //Object[] DEFAULT_CAP = new Object[]{}; ----> [] - 0 length array
    //Object[] DEFAULT_CAP = new Object[]{2}; ----> [2] - array with initial value
    //Object[] DEFAULT_CAP = new Object[3]; ----> [null, null, null] - array with initial size
    private static final Object[] DEFAULT_CAP = {};
    private Object[] dataArray;

    public AList(Integer capacity) throws IllegalArgumentException{
        if(capacity == 0){
            this.dataArray = DEFAULT_CAP;
        } else if(capacity > 0){
            this.dataArray = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Size cannot be less than zero");
        }
    }

    public AList(){
        this.dataArray = DEFAULT_CAP;
    }

    public AList(Collection<? extends E> collection){
        this.dataArray = DEFAULT_CAP;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            this.add((E)iterator.next());
        }
    }

    @Override
    public E get(int index){
        if(index >= 0){
            return (E)dataArray[index];
        } else {
            return null;
        }
    }

    @Override
    public int size(){
        int size = 0;
        for(Object element : dataArray){
            if(element != null){
                size++;
            } else {
                break;
            }
        }
        return size;
    }

    @Override
    public boolean add(E element){
        if(isPlaceForNext()){
            dataArray[this.size()] = element;
        } else {
            stretchArray();
            dataArray[this.size()] = element;
        }
        return true;
    }

    private boolean stretchArray(){
        if(this.dataArray.length == 0){
            dataArray = Arrays.copyOf(dataArray, 1);
        } else {
            dataArray = Arrays.copyOf(dataArray, (int) Math.ceil(this.dataArray.length * 1.5));
        }
        return true;
    }

    private boolean isPlaceForNext(){
        return this.dataArray.length > this.size();
    }

}
