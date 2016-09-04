package com.steveq;

import java.util.*;

/**
 * Created by SteveQ on 2016-09-03.
 */
public class AList<E> extends AbstractList<E> implements Collection<E>{
    //Other possibilities to declare array:
    //Object[] DEFAULT_CAP = new Object[]{}; ----> [] - 0 length array
    //Object[] DEFAULT_CAP = new Object[]{2}; ----> [2] - array with initial value
    //Object[] DEFAULT_CAP = new Object[3]; ----> [null, null, null] - array with initial size
    private static final Object[] DEFAULT_CAP = {};
    public Object[] dataArray;

    //----- CONSTRUCTORS -----//
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

    //----- CONSTRUCTORS -----//


    //----- EXPOSED METHODS -----//
    @Override
    public E get(int index) throws IllegalArgumentException{
        if(index >= 0 && index < this.size()){
            return (E)dataArray[index];
        } else {
            throw new IllegalArgumentException("Wrong index");
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

    @Override
    public E remove(int index){
        int moved = this.size() - index - 1;
        E removed = (E) this.dataArray[index];
        System.arraycopy(this.dataArray, index + 1, this.dataArray, index, moved);
        dataArray = Arrays.copyOf(dataArray, size() - 1);
        return removed;
    }

    public boolean remove(Object object){
        for(int i=0; i < this.size(); i++){
            if(this.dataArray[i].equals(object)){
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear(){
        int iterate = this.size();
        for(int i=0; i < iterate; i++){
            this.remove(0);
        }
    }

    @Override
    public boolean contains(Object object){
        Iterator iterator = this.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(object)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object object){
        for(int i=0; i<this.size(); i++){
            if(this.dataArray[i].equals(object)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator(){
        return new Iterator<E>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < AList.this.dataArray.length;
            }

            @Override
            public E next() throws NoSuchElementException {
                if(this.hasNext()){
                    return AList.this.get(cursor++);
                }
                throw new NoSuchElementException();
            }
        };
    }
    //----- EXPOSED METHODS -----//


    //----- HELPER METHODS -----//
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
    //----- HELPER METHODS -----//

}
