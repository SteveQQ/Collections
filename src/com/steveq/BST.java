package com.steveq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by SteveQ on 2017-01-21.
 */
public class BST<E extends Comparable<E>> implements Collection<E>{

    private int numElements;
    private Node absRoot;
    private Node curRoot;

    public class Node implements Iterable{
        private E data;
        private Node leftJoin;
        private Node rightJoin;
        private Node parentJoin;
        private int subtreeSize;

        public Node(E data, Node parent) {
            this.data = data;
            this.parentJoin = parent;
            this.leftJoin = null;
            this.rightJoin = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getLeftJoin() {
            return leftJoin;
        }

        public void setLeftJoin(Node leftJoin) {
            this.leftJoin = leftJoin;
        }

        public Node getRightJoin() {
            return rightJoin;
        }

        public void setRightJoin(Node rightJoin) {
            this.rightJoin = rightJoin;
        }

        public Node getParentJoin() {
            return parentJoin;
        }

        public void setParentJoin(Node parentJoin) {
            this.parentJoin = parentJoin;
        }

        public int getSubtreeSize() {
            return subtreeSize;
        }

        public void setSubtreeSize(int subtreeSize) {
            this.subtreeSize = subtreeSize;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class BSTIterator implements Iterator{

        private int cursor = 0;
        private Object[] elements = toArray();

        @Override
        public boolean hasNext() {
            return elements[cursor+1] != null;
        }

        @Override
        public Object next() {
            if(elements[cursor] != null){
                return elements[cursor++];
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return new BSTIterator();
    }

    @Override
    public Object[] toArray() {
        return traverseInOrder(absRoot);
    }

    @Override
    public boolean add(E e) {
        if(absRoot == null){
            absRoot = new Node(e, null);
            numElements++;
            return true;
        } else {
            curRoot = absRoot;
            while(true){           //until reach leaf
                if (e.compareTo(curRoot.getData()) <= 0) {
                    if(curRoot.getLeftJoin() == null){          //empty LEFT slot found
                        curRoot.setLeftJoin(new Node(e, curRoot));
                        numElements++;
                        return true;
                    } else {
                        curRoot = curRoot.getLeftJoin();
                    }
                } else {
                    if(curRoot.getRightJoin() == null){         //empty RIGHT slot found
                        curRoot.setRightJoin(new Node(e, curRoot));
                        numElements++;
                        return true;
                    } else {
                        curRoot = curRoot.getRightJoin();
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        E e = (E)o;
        curRoot = absRoot;
        while(true){           //until reach leaf
            if(e.compareTo(curRoot.getData()) == 0){
                if(curRoot.getLeftJoin() != null){
                    curRoot.getLeftJoin().setRightJoin(curRoot.getRightJoin());
                    if(curRoot.getParentJoin() != null) {
                        if (curRoot.equals(curRoot.getParentJoin().getLeftJoin())) {
                            curRoot.getParentJoin().setLeftJoin(curRoot.getLeftJoin());
                        } else {
                            curRoot.getParentJoin().setRightJoin(curRoot.getLeftJoin());
                        }
                    } else {
                        absRoot = curRoot.getLeftJoin();
                    }
                    numElements--;
                    return true;
                } else {
                    if(curRoot.getParentJoin() != null) {
                        if (curRoot.getParentJoin().getLeftJoin().equals(curRoot)) {
                            curRoot.getParentJoin().setLeftJoin(curRoot.getRightJoin());
                        } else {
                            curRoot.getParentJoin().setRightJoin(curRoot.getRightJoin());
                        }
                    } else {
                        absRoot = curRoot.getRightJoin();
                    }
                    numElements--;
                    return true;
                }
            } else if (e.compareTo(curRoot.getData()) <= 0) {
                if(curRoot.getLeftJoin() == null){          //empty LEFT slot found
                    return false;
                } else {
                    curRoot = curRoot.getLeftJoin();
                }
            } else {
                if(curRoot.getRightJoin() == null){         //empty RIGHT slot found
                    return false;
                } else {
                    curRoot = curRoot.getRightJoin();
                }
            }
        }
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    private Object[] traverseInOrder(Node startRoot) {
        ArrayList<Object> result = new ArrayList<>();
        if(startRoot.getLeftJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traverseInOrder(startRoot.getLeftJoin()))));
        }
        result.add(startRoot);
        if(startRoot.getRightJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traverseInOrder(startRoot.getRightJoin()))));
        }
        return result.toArray();
    }

    private Object[] traversePreorder(Node startRoot){
        ArrayList<Object> result = new ArrayList<>();

        result.add(startRoot);

        if(startRoot.getLeftJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traversePreorder(startRoot.getLeftJoin()))));
        }

        if(startRoot.getRightJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traversePreorder(startRoot.getRightJoin()))));
        }
        return result.toArray();
    }

    private Object[] traversePostorder(Node startRoot){
        ArrayList<Object> result = new ArrayList<>();

        if(startRoot.getLeftJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traversePostorder(startRoot.getLeftJoin()))));
        }

        if(startRoot.getRightJoin() != null){
            result.addAll(new ArrayList<Object>(Arrays.asList(traversePostorder(startRoot.getRightJoin()))));
        }

        result.add(startRoot);

        return result.toArray();
    }

    @Override
    public String toString() {

        Object[] collection = toArray();
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for(int i=0; i < collection.length; i++){
            builder.append(((Node)collection[i]).toString());
            if(i < collection.length-1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
