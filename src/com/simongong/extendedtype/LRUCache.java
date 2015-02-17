package com.simongong.extendedtype;

import java.util.HashMap;
import java.util.Map;

/*
Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and set.
get(key)
Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value)
Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

思路：
Cache本质是一个hashmap，所以我们需要一个hashmap来实现基本的set/get操作
Cache管理策略一般可以用某个数据结构来实现，根据LRU策略的动作，可以用一个链表来实现。
每次set/get某个元素时，就将其移动到链表尾部，这样就完成了按LRU排序的目的。当需要remove元素时，移出链表头部元素即可。
综合起来，用hashmap的value存储对应的链表元素。
 */
public class LRUCache {
    Map<Integer, DListNode> map;
    DListNode head, tail;
    int capacity;
    
    public LRUCache(int capacity){
        this.capacity = capacity;
        
        this.map = new HashMap<Integer, DListNode>();
        this.head = new DListNode(-1, -1);
        this.tail = new DListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }
    
    public int get(int key){
        if(!this.map.containsKey(key)){
            return -1;
        }
        // update node list
        DListNode node = map.get(key);
        removeNode(node);
        addToTail(node);
        
        return node.val;
    }
    
    public void set(int key, int value){
        DListNode node = null;
        if(this.map.containsKey(key)){  // update node
            node = map.get(key);
            removeNode(node);
            node.val = value;
        }else{  // insert node at tail
            node = new DListNode(key, value);
            this.map.put(key, node);
        }
        addToTail(node);
        
        // remove old cache if capacity is overflow
        if(this.map.size() > this.capacity){
            this.map.remove(head.next.key);
            removeFirst();
        }
    }
    
    public void removeFirst(){
        removeNode(head.next);
    }
    
    public void removeNode(DListNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void addToTail(DListNode node){
        this.tail.pre.next = node;
        node.pre = this.tail.pre;
        node.next = this.tail;
        this.tail.pre = node;
    }
}

class DListNode {
    DListNode pre;
    DListNode next;
    int val;
    int key;
    DListNode(int key, int val){
        this.val = val;
        this.key = key;
        pre = null;
        next = null;
    }
}
