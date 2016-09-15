package com.steveq;


import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ListIterator;

/**
 * Created by SteveQ on 2016-09-14.
 */

public class LinList<E> extends AbstractSequentialList<E>{

    Node mFirstNode;
    Node mLastNode;
    private int mSize;
    private int modCount;

    //-----CONSTRUCTORS-----//
    public LinList(){
        Collections.emptyList();
    }
    //-----CONSTRUCTORS-----//

    //-----INNER CLASSES-----//
    private class Node{
        Node mNextNode;
        Node mPrevNode;
        E mData;

        public Node(Node prev, E data, Node next){
            mPrevNode = prev;
            mData = data;
            mNextNode = next;
        }

        @Override
        public String toString() {
            return mData.toString();
        }
    }
    //-----INNER CLASSES-----//

    //-----METHODS-----//

    public ListIterator<E> listIterator(int index){
        return new listIterator(index);
    }

    public ListIterator<E> listIterator(){
        return new listIterator(0);
    }

    public class listIterator implements ListIterator<E>{

        private Node nextNode;
        private Node prevNode;
        private int mIndex;

        public listIterator(int index) throws IndexOutOfBoundsException{
            nextNode = mFirstNode;
            if (index > 0 && index < mSize) {
                for (int i = 0; i < index; i++) {
                    next();
                }
            } else if (index == 0) {
                nextNode = mFirstNode;
            } else {
                throw new IndexOutOfBoundsException("You choose too big index");
            }
        }

        public boolean hasNext(){
            return nextNode != null;
        }

        public boolean hasPrevious(){
            return nextNode.mPrevNode != null;
        }

        public E next(){
            mIndex++;
            E data = nextNode.mData;
            prevNode = nextNode;
            nextNode = nextNode.mNextNode;
            return data;
        }

        public E previous(){
            mIndex--;
            E data = prevNode.mData;
            nextNode = prevNode;
            prevNode = prevNode.mPrevNode;
            return data;
        }

        public int nextIndex(){
            if(mIndex < mSize){
                return size();
            } else {
                return mIndex++;
            }
        }

        public int previousIndex(){
            if(mIndex == 0){
                return size();
            } else {
                return mIndex--;
            }
        }

        public void set(E e){

        }

        public void add(E e){

        }

        public void remove(){

        }
    }

    @Override
    public int size(){
        return mSize;
    }

    @Override
    public boolean isEmpty(){
        return mSize == 0;
    }

    public void addFirst(E element){
        Node fNode = mFirstNode;
        Node newNode = new Node(null, element, mFirstNode);
        mFirstNode = newNode;
        if(fNode == null){
            mLastNode = newNode;
        } else {
            fNode.mPrevNode = newNode;
        }
        mSize++;
        modCount++;
    }
    //-----METHODS-----//

}
