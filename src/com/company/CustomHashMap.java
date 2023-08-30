package com.company;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;

public class CustomHashMap<K, V> {

    private ArrayList<Entry<K, V>>[] storage;
    private int capacity;
    private int size;

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        storage = new ArrayList[capacity];
        size = 0;
    }

    public void insert(K key, V value) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucket = storage[index];
        if (bucket == null) {
            bucket = new ArrayList<Entry<K, V>>();
            storage[index] = bucket;
        }
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<K, V>(key, value));
        size++;
    }

    public void update(K key,V value){
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucket = storage[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucket = storage[index];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void delete(K key) {
        int index = getIndex(key);
        ArrayList<Entry<K, V>> bucket = storage[index];
        if (bucket == null) {
            return;
        }
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    public int getSize() {
        return size;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public K[] getKeys(Class<K> clazz){
        K[] keys = (K[]) Array.newInstance(clazz, size);
        int i = 0;
        for(int j=0;j<capacity;j++ ){
            if(storage[j]!=null){
                for(int k=0;k<storage[j].size();k++){
                    keys[i] = storage[j].get(k).getKey();
                    i++;
                }
            }
        }
        return keys;
    }

    public String toString(){
        String s="";
        for(int i=0;i<capacity;i++ ){
            if(storage[i]!=null){
                for(int j=0;j<storage[i].size();j++){
                    System.out.println(storage[i].get(j).getKey()+" "+storage[i].get(j).getValue());
                }
            }
        }
        return s;
    }

    public ArrayList<Entry<K, V>>[] getStorage(){
        return storage;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }


}
