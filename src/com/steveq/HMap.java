package com.steveq;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by SteveQ on 2016-10-01.
 */
public class HMap<K,V> extends AbstractMap<K,V> {

     myEntry<K,V>[] mEntries;
    private final int INITIAL_CAP = 16;
    private final double LOAD_FACTOR = 0.75;

    public HMap() {
        mEntries = new myEntry[INITIAL_CAP];
    }

    class myEntry<K,V> implements Map.Entry<K,V>{
        private K mKey;
        private V mValue;
        private Map.Entry next;

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

            if (mKey != null ? !mKey.equals(myEntry.mKey) : myEntry.mKey != null) return false;
            return mValue != null ? mValue.equals(myEntry.mValue) : myEntry.mValue == null;

        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + (mKey != null ? mKey.hashCode() : 0);
            return hash;
        }
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet(){
        Set<Map.Entry<K,V>> set = new HashSet<>();
        for(myEntry en : mEntries){
            if(en != null){
                set.add(en);

            }
        }
        return set;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(int i=0; i < mEntries.length; i++){
            set.add(mEntries[i].getKey());
        }
        return set;
    }

    @Override
    public V put(K key, V value){

        if(entrySet().size() >= mEntries.length * LOAD_FACTOR){
            reorganizeSet();
        }
        myEntry<K,V> newEntry = new myEntry<>(key, value);
        int hash = newEntry.hashCode();
        System.out.println(hash % mEntries.length);
        mEntries[hash % mEntries.length] = newEntry;

        return null;
    }


    @Override
    public V get(Object key){
        myEntry<K,V> e = new myEntry<>((K)key, null);
        int hash = e.hashCode();
        System.out.println(hash % mEntries.length);
        V v =mEntries[hash % mEntries.length].getValue();
        return v;
    }

    private void reorganizeSet() {
        myEntry<K,V>[] newArray = new myEntry[mEntries.length*2];
        myEntry<K,V> en = null;
        for(Map.Entry<K, V> entry : entrySet())
            en = new myEntry<>(entry.getKey(), entry.getValue());
        int hash = en.hashCode();
        newArray[hash % mEntries.length] = en;
    }

    public boolean isEmpty(){
        return entrySet().size() == 0;
    }

}
