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
    public Node absRoot;
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
            this.subtreeSize++;
            recurseIncreaseSize(this.parentJoin);
        }

        public void recurseIncreaseSize(Node parent){
            if(parent == null){
                return;
            }
            recurseIncreaseSize(parent.getParentJoin());
            parent.setSubtreeSize(parent.getSubtreeSize() + 1);
            return;
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
        Node node = findNode((E)o);
        if(node.getData().compareTo((E)o) == 0){
            return true;
        }
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
        Node placeHolder = findNode(e, absRoot);
        if(placeHolder == null){
            absRoot = new Node(e, null);
            numElements++;
            absRoot.setSubtreeSize(1);
            return true;
        }
        if(placeHolder.getData().compareTo(e) == 0) {
            if (placeHolder.getLeftJoin() != null) {
                Node subPlaceHolder = findNode(e, placeHolder.getLeftJoin());
                if (subPlaceHolder.getData().compareTo(e) <= 0){
                    subPlaceHolder.setLeftJoin(new Node(e, subPlaceHolder));
                    numElements++;
                    return true;
                } else{
                    subPlaceHolder.setRightJoin(new Node(e, subPlaceHolder));
                    numElements++;
                    return true;
                }
            } else {
                placeHolder.setLeftJoin(new Node(e, placeHolder));
                numElements++;
                return true;
            }
        } else {
            if(e.compareTo(placeHolder.getData()) <= 0){
                placeHolder.setLeftJoin(new Node(e, placeHolder));
                numElements++;
                return true;
            } else{
                placeHolder.setRightJoin(new Node(e, placeHolder));
                numElements++;
                return true;
            }
        }
    }

    public int height(){
        return height(absRoot.getData());
    }

    public int height(E e){
        Node curNode = findNode(e);

        int result = 0;
        while(true){
            if(curNode.getLeftJoin() != null || curNode.getRightJoin() != null){
                if (curNode.getLeftJoin() == null) {
                    curNode = curNode.getRightJoin();
                    result++;
                } else if(curNode.getRightJoin() == null){
                    curNode = curNode.getLeftJoin();
                    result++;
                } else {
                    if(curNode.getLeftJoin().getSubtreeSize() >= curNode.getRightJoin().getSubtreeSize()){
                        curNode = curNode.getLeftJoin();
                        result++;
                    } else {
                        curNode = curNode.getRightJoin();
                        result++;
                    }
                }
            } else {
                return result;
            }
        }
    }

    public Node findNode(E e){
        return findNode(e, absRoot);
    }

    private Node findNode(E e, Node root){
        if(absRoot != null) {
            curRoot = root;

            while(true){
                if(e.compareTo(curRoot.getData()) == 0){
                    return curRoot;
                } else if(e.compareTo(curRoot.getData()) < 0){
                    if(curRoot.getLeftJoin() != null) {
                        curRoot = curRoot.getLeftJoin();
                    } else {
                        return curRoot;
                    }
                } else if(e.compareTo(curRoot.getData()) > 0){
                    if(curRoot.getRightJoin() != null) {
                        curRoot = curRoot.getRightJoin();
                    } else {
                        return curRoot;
                    }
                }
            }
        }
        else {
            return null;
        }
    }

    public E findMinimum(){
        Node minNode = findMinimum(absRoot);
        return minNode.getData();
    }

    public Node findMinimum(Node root){
        Node curNode = root;
        while(curNode.getLeftJoin() != null){
            curNode = curNode.getLeftJoin();
        }
        return curNode;
    }

    public E findMaximum(){
        Node maxNode = findMaximum(absRoot);
        return maxNode.getData();
    }

    public Node findMaximum(Node root){
        Node curNode = root;
        while(curNode.getRightJoin() != null){
            curNode = curNode.getRightJoin();
        }
        return curNode;
    }


    private int getNodeSide(Node node){
        if(node.getParentJoin() != null) {
            if (node.equals(node.getParentJoin().getLeftJoin())) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private boolean removeWhenZeroChildren(Node nodeToRemove){
        switch(getNodeSide(nodeToRemove)){
            case -1:
                nodeToRemove.getParentJoin().setLeftJoin(null);
                break;
            case 0:
                absRoot = nodeToRemove.getRightJoin();
                break;
            case 1:
                nodeToRemove.getParentJoin().setRightJoin(null);
                break;
            default:
                break;
        }
        numElements--;
        return true;
    }

    private boolean removeWhenOneChildren(Node nodeToRemove){
        if(nodeToRemove.getLeftJoin() != null){
            switch (getNodeSide(nodeToRemove)){
                case -1:
                    nodeToRemove.getParentJoin().setLeftJoin(nodeToRemove.getLeftJoin());
                    break;
                case 0:
                    absRoot = nodeToRemove.getLeftJoin();
                    absRoot.setParentJoin(null);
                    break;
                case 1:
                    nodeToRemove.getParentJoin().setRightJoin(nodeToRemove.getLeftJoin());
                    break;
                default:
                    break;
            }
        } else {
            switch (getNodeSide(nodeToRemove)){
            case -1:
                nodeToRemove.getParentJoin().setLeftJoin(nodeToRemove.getRightJoin());
                break;
            case 0:
                absRoot = nodeToRemove.getRightJoin();
                absRoot.setParentJoin(null);
                break;
            case 1:
                nodeToRemove.getParentJoin().setRightJoin(nodeToRemove.getRightJoin());
                break;
            default:
                break;
            }
        }
        numElements--;
        return true;
    }

    private boolean removeWhenTwoChildren(Node nodeToRemove){
        Node replacementNode = findMinimum(nodeToRemove.getRightJoin());
        nodeToRemove.setData(replacementNode.getData());
        return removeWhenZeroChildren(replacementNode);
    }

    private int childrenNumber(Node root){
        if(root.getLeftJoin() == null &&            //zero children case
                root.getRightJoin() == null) return 0;
        else if((root.getLeftJoin() == null && root.getRightJoin() != null) ||       //one children case
                (root.getLeftJoin() != null && root.getRightJoin() == null)) return 1;
        else return 2;
    }

    @Override
    public boolean remove(Object o) {
        E e = (E)o;
        Node nodeToRemove;
        if(contains(e)){

            nodeToRemove = findNode(e);

            switch (childrenNumber(nodeToRemove)){
                case 0:
                    return removeWhenZeroChildren(nodeToRemove);
                case 1:
                    return removeWhenOneChildren(nodeToRemove);
                case 2:
                    return removeWhenTwoChildren(nodeToRemove);
                default:
                    return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection c) {

        Iterator it = c.iterator();

        while(it.hasNext()){
            if(!add((E)it.next()))
                return false;
        }

        return true;

    }

    @Override
    public void clear() {
        Object[] elementsSet = toArray();
        for(int i=0; i < elementsSet.length; i++){
            remove(((Node)elementsSet[i]).getData());
        }
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

    public E ceiling(E e){

        Node nodeForCeiling = findNode(e);

        if(e.compareTo(nodeForCeiling.getLeftJoin().getData()) == 0 ||
                (nodeForCeiling.equals(absRoot) && nodeForCeiling.getRightJoin() == null)){
            return nodeForCeiling.getData();
        } else if (nodeForCeiling.getRightJoin() != null){
            return nodeForCeiling.getRightJoin().getData();
        } else {
            return nodeForCeiling.getParentJoin().getData();
        }
    }

    public E floor(E e){
        Node nodeForFloor = findNode(e);

        if(nodeForFloor.getLeftJoin() == null ||
                e.compareTo(nodeForFloor.getLeftJoin().getData()) == 0){
            return nodeForFloor.getData();
        } else {
            return findMaximum(nodeForFloor.getLeftJoin()).getData();
        }
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
