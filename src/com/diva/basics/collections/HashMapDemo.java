package com.diva.basics.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapDemo {
    public static void main(String ... args) {
       
        String words = "instagram snapchat facebook linkedin Gmail";
        String[] wordArr = words.split(" ");

        //Word -> Length
        Map<String, Integer> wordMap = new TreeMap<>();
        for(int i = 0; i < wordArr.length; i++) {
            wordMap.put(wordArr[i], wordArr[i].length());
        }
        System.out.println("Word map ---> " +wordMap);

        //Word -> Frequency
        Map<String, Integer> freqMap = new TreeMap<>();
        for(String s : words.split("")) {
            freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        }
        System.out.println("Freq map ---> " +freqMap);

    }
}

class MyHashMap<K,V> {

    private static class Node<K,V> {
        int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K,V>[] buckets;
    private int capacity = 16;

    public MyHashMap(){
        buckets = new Node[capacity];
    }

    public int getBucketIndex(K key) {
        return key.hashCode() & (capacity - 1);
    }

    public void put(K key, V value) {
        int hash = getBucketIndex(key);



    }

}
