package com.steveq;


import java.lang.reflect.Array;
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
    private ListIterator<E> mIterator;

    //-----CONSTRUCTORS-----//
    public LinList(){
        Collections.emptyList();
        mIterator = listIterator();
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
            if(index <= mSize/2){
                nextNode = mFirstNode;
                while(mIndex < index){
                    next();
                }
            } else if(index > mSize/2){
                prevNode = mLastNode;
                mIndex = mSize;
                while(mIndex > index){
                    previous();
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
                returnedNode = nextNode;
                prevNode = nextNode;
                nextNode = nextNode.mNextNode;
                return data;
            } else {
                return null;
            }
        }

        public E previous(){
            if(hasPrevious()){
                mIndex--;
                returnedNode = prevNode;
                E data = prevNode.mData;
                nextNode = prevNode;
                prevNode = prevNode.mPrevNode;
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
            if(hasPrevious() && hasNext()) {
                prevNode.mNextNode = newNode;
                nextNode.mPrevNode = newNode;
                newNode.mPrevNode = prevNode;
                newNode.mNextNode = nextNode;
                prevNode = newNode;
            } else if(mSize == 0) {
                prevNode = newNode;
                mFirstNode = newNode;
                mLastNode = newNode;
                newNode.mPrevNode = null;
                newNode.mNextNode = null;
            } else if(mIndex == 0){
                newNode.mNextNode = mFirstNode;
                newNode.mPrevNode = null;
                mFirstNode.mPrevNode = newNode;
                prevNode = newNode;
                mFirstNode = newNode;
            } else if(mIndex == mSize){
                newNode.mPrevNode = mLastNode;
                newNode.mNextNode = null;
                mLastNode.mNextNode = newNode;
                prevNode = newNode;
                mLastNode = newNode;
            }
            mSize++;
            modCount++;
        }

        public void remove() {
            if(returnedNode != null) {
                if(mFirstNode == mLastNode) {
                    mFirstNode = null;
                    mLastNode = null;
                } else   if(returnedNode == mFirstNode){
                    nextNode = returnedNode.mNextNode;
                    prevNode = null;
                    mFirstNode = returnedNode.mNextNode;
                    returnedNode.mNextNode.mPrevNode = mFirstNode;
                } else if(returnedNode == mLastNode){
                    nextNode = null;
                    prevNode = returnedNode.mPrevNode;
                    returnedNode.mPrevNode.mNextNode = null;
                    mLastNode = returnedNode.mPrevNode;
                } else if(mFirstNode == mLastNode){
                    mFirstNode = null;
                    mLastNode = null;
                } else {
                    returnedNode.mPrevNode.mNextNode = returnedNode.mNextNode;
                    returnedNode.mNextNode.mPrevNode = returnedNode.mPrevNode;
                    nextNode = returnedNode.mNextNode;
                    prevNode = returnedNode.mPrevNode;
                }
                returnedNode = null;
                mSize--;
                mIndex--;
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

    public boolean contains(Object o){
        E element = (E)o;
        ListIterator<E> it = listIterator();
        while(it.hasNext()) {
            if (it.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void clear(){
        ListIterator<E> it = listIterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }

    public void addFirst(E element){
        ListIterator<E> it = listIterator();
        it.add(element);
    }

    public void addLast(E element){
        ListIterator<E> it = listIterator(mSize);
        it.add(element);
    }

    public boolean add(E element){
        addLast(element);
        return true;
    }

    public void add(int index, E element) throws IndexOutOfBoundsException{
        if(index < mSize && index >=0) {
            ListIterator<E> it = listIterator(index);
            it.add(element);
        } else if(index == mSize){
            addLast(element);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E removeFirst(){
        return remove();
    }

    public E removeLast(){
        ListIterator<E> it = listIterator(mSize);
        E result = it.previous();
        it.remove();
        return  result;
    }

    public E remove() {
        ListIterator<E> it = listIterator();
        E result = it.next();
        it.remove();
        return result;
    }

    public boolean remove(Object object){
        E element = (E)object;
        boolean result = false;
        ListIterator<E> it = listIterator();
        do{
            result = true;
        } while (!it.next().equals(element));
        it.remove();
        return result;
    }

    public E remove(int index) throws IndexOutOfBoundsException{
        if(index < mSize && index >= 0){
            ListIterator<E> it = listIterator(index);
            E result = it.next();
            it.remove();
            return result;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public E getFirst(){
        ListIterator<E> it = listIterator();
        return it.next();
    }

    public E getLast(){
        ListIterator<E> it = listIterator(mSize);
        return it.previous();
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index < mSize && index >= 0){
            ListIterator<E> it = listIterator(index);
            return it.next();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    //-----METHODS-----//

}
