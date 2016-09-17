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
        private Node returnedNode;
        private int mIndex;

        public listIterator(int index) throws IndexOutOfBoundsException{
            nextNode = mFirstNode;
            if(index == 0){
                nextNode = mFirstNode;
            } else if(index < mSize){
                while(nextIndex() < index+1){
                    next();
                }
            } else {
                throw new IndexOutOfBoundsException("You choose too big index");
            }
        }

        public boolean hasNext(){
            return nextNode != null;
        }

        public boolean hasPrevious(){
            return prevNode != null;
        }

        public E next(){
            if(hasNext()) {
                mIndex++;
                E data = nextNode.mData;
                prevNode = nextNode;
                nextNode = nextNode.mNextNode;
                returnedNode = prevNode;
                return data;
            } else {
                return null;
            }
        }

        public E previous(){
            if(hasPrevious()){
                mIndex--;
                E data = prevNode.mData;
                nextNode = prevNode;
                prevNode = prevNode.mPrevNode;
                returnedNode = nextNode;
                return data;
            }
            return null;
        }

        public int nextIndex(){
            if(mIndex >= mSize){
                return size();
            } else {
                int nInd = mIndex + 1;
                return nInd;
            }
        }

        public int previousIndex(){
            if(mIndex == 0){
                return size();
            } else {
                int nInd = mIndex - 1;
                return nInd;
            }
        }

        public void set(E e){
            returnedNode.mData = e;
        }

        public void add(E e){
            Node newNode = new Node(prevNode, e, nextNode);
            if(hasPrevious()) {
                prevNode.mNextNode = newNode;
                nextNode.mPrevNode = newNode;
                newNode.mPrevNode = nextNode.mPrevNode;
                newNode.mNextNode = nextNode;
            } else {
                mFirstNode = newNode;
                nextNode.mPrevNode = newNode;
                newNode.mPrevNode = null;
                newNode.mNextNode = nextNode;
            }
            prevNode = newNode;
            mSize++;
            modCount++;
        }

        public void remove() {
            if(returnedNode != null) {
                if (!hasPrevious() && mSize > 1) {
                    prevNode = null;
                    nextNode = returnedNode.mNextNode;
                    mFirstNode = returnedNode.mNextNode;
                    returnedNode.mNextNode.mPrevNode = null;
                } else if(!hasPrevious() && mSize == 1){
                    mFirstNode = null;
                    mLastNode = null;
                } else if (!hasNext()) {
                    nextNode = null;
                    prevNode = returnedNode.mPrevNode;
                    returnedNode.mPrevNode.mNextNode = null;
                    mLastNode = returnedNode.mPrevNode;
                } else {
                    nextNode = returnedNode.mNextNode;
                    prevNode = returnedNode.mPrevNode;
                    returnedNode.mPrevNode.mNextNode = returnedNode.mNextNode;
                    returnedNode.mNextNode.mPrevNode = returnedNode.mPrevNode;
                }
                returnedNode = null;
                mSize--;
                modCount++;
            }
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
        Node head = mFirstNode;
        Node newNode = new Node(null, element, mFirstNode);
        mFirstNode = newNode;
        if(head == null){
            mLastNode = newNode;
        } else {
            head.mPrevNode = newNode;
        }
        mSize++;
        modCount++;
    }
    //-----METHODS-----//

}
