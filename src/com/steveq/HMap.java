package com.steveq;

import java.util.*;

/**
 * Created by SteveQ on 2016-10-01.
 */
public class HMap<K,V> extends AbstractMap<K,V> {

    private final int INITIAL_CAP = 4;
    private final double LOAD_FACTOR = 0.75;

    LinkedList<myEntry<K,V>>[] hashtable;

    @SuppressWarnings("unchecked")
    public HMap() {
        hashtable = new LinkedList[INITIAL_CAP];
    }

    class myEntry<K,V> implements Map.Entry<K,V>{
        private K mKey;
        private V mValue;

        public myEntry(K key, V value) {
            mKey = key;
            mValue = value;
        }

        @Override
        public K getKey() {
            return mKey;
        }

        public void setKey(K key) {
            mKey = key;
        }

        @Override
        public V getValue() {
            return mValue;
        }

        @Override
        public V setValue(V value) {
            mValue = value;
            return null;
        }

        @Override
        public String toString() {
            return mKey +
                    "-" +
                    mValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            myEntry<?, ?> myEntry = (myEntry<?, ?>) o;

            return mKey != null ? mKey.equals(myEntry.mKey) : myEntry.mKey == null;

        }

        @Override
        public int hashCode() {
//            if(Integer.parseInt(mKey.toString()) >= 3){
//                return 5;
//            }
            int hash = 17;
            hash = 31 * hash + (mKey != null ? mKey.hashCode() : 0);
            return hash;
        }
    }

    @Override
    public Set<Entry<K,V>> entrySet(){
        Set<Entry<K,V>> set = new HashSet<>();
        myEntry<K,V> nextEntry = null;
        for(LinkedList<myEntry<K,V>> bucket : hashtable){
            if(bucket != null) {
                ListIterator<myEntry<K, V>> it = bucket.listIterator();
                while (it.hasNext()) {
                    set.add(it.next());
                }
            }
        }
        return set;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(LinkedList<myEntry<K,V>> bucket : hashtable) {
            ListIterator<myEntry<K,V>> it = bucket.listIterator();
            if(bucket != null){
                while(it.hasNext()){
                    set.add(it.next().getKey());
                }
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value){

        myEntry<K,V> newEntry = new myEntry<>(key, value);
        int hash = newEntry.hashCode();

        LinkedList<myEntry<K, V>> bucket;

        if(getIndex(hash) >= hashtable.length) {
            bucket = null;
        } else {
            bucket = hashtable[getIndex(hash)];
        }

        if(bucketsNumber() >= hashtable.length * LOAD_FACTOR){
            reorganizeHashTable();
        }

        if(bucket == null){
            bucket = new LinkedList<>();
            bucket.addFirst(newEntry);
        } else {
            ListIterator<myEntry<K,V>> it = bucket.listIterator();
            while(it.hasNext()){
                myEntry<K,V> old = it.next();
                if(newEntry.equals(old)){
                    bucket.set(bucket.indexOf(it.previous()), newEntry);
                    return old.getValue();
                } else {
                    bucket.addFirst(newEntry);
                    break;
                }
            }
        }

        hashtable[getIndex(hash)] = bucket;

        return null;
    }

    private int bucketsNumber() {
        int result = 0;
        for(LinkedList<myEntry<K,V>> el : hashtable){
            if(el != null){
                result++;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private void reorganizeHashTable() {
        LinkedList<myEntry<K,V>>[] newTable = new LinkedList[hashtable.length * 2];
        for(int i=0; i < hashtable.length; i++){
            newTable[i] = hashtable[i];
        }
        hashtable = newTable;
    }


    @Override
    public V get(Object key){

        myEntry<K,V> e = new myEntry<>((K)key, null);
        int hash = e.hashCode();

        LinkedList<myEntry<K,V>> bucket = hashtable[getIndex(hash)];

        ListIterator<myEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()){
            myEntry<K,V> next = it.next();
            if(e.equals(next)){
                return next.getValue();
            }
        }

        return null;
    }

    private int getIndex(int hash) {
        return hash % hashtable.length;
    }

    public boolean isEmpty(){
        return entrySet().size() == 0;
    }

    public void clear(){
        for(int i=0; i < hashtable.length; i++){
            hashtable[i] = null;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key){
        myEntry<K,V> e = new myEntry<>((K)key, null);
        int hash = e.hashCode();

        LinkedList<myEntry<K,V>> bucket = hashtable[getIndex(hash)];

        if(bucket != null) {
            ListIterator<myEntry<K, V>> it = bucket.listIterator();
            while (it.hasNext()) {
                myEntry<K, V> next = it.next();
                if (e.equals(next)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    public boolean containsValue(Object value){

        for(LinkedList<myEntry<K,V>> en : hashtable){
            if(en != null) {
                ListIterator<myEntry<K, V>> it = en.listIterator();
                while (it.hasNext()) {
                    if (it.next().getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
