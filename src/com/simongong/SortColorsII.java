package com.simongong;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/*
Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

注意
You are not suppose to use the library's sort function for this problem.

样例
GIven colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4]. 

挑战
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory.

Can you do it without using extra memory?

思路：
处理思路还是取自桶排序思想。跟Sort Colors类似，只是做了一个扩展，可以排任意k组数据。
依然是先计算k组数据各自的总数目：n1/c1, n2/c2, ..., nk/ck
然后对[n1,...,nk]排序，根据排序结果，对数组进行划分为k个区域
然后扫描数组，需要维护k个变量，把每个数对号入座

实现时，为了方便，使用HashMap<number, counts>来装这k个元素，使用HashMap<number, index>来追踪每一区域的尾部
 */
public class SortColorsII {

    public void sort(int[] data, int colors){
        if(data == null || colors <= 0 || data.length <= colors){
            return;
        }
        
        // initial  an array for colors
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < data.length ; i++){
            if(map.containsKey(data[i])){
                map.put(data[i], map.get(data[i]) + 1);
            }else{
                map.put(data[i], 1);
            }
        }
        
        Integer[] k = new Integer[colors];
        map.keySet().toArray(k);
        Arrays.sort(k);
        
        // prepare HashMap<number, index>
        int cursor = 0;
        HashMap<Integer, Integer> cursorMap = new HashMap<Integer, Integer>();
        for(Entry<Integer, Integer> entry: map.entrySet()){
            cursorMap.put(entry.getKey(), cursor);
            cursor += entry.getValue();
        }
        
        for(int i = 0; i < data.length ; i++){
            int position = cursorMap.get(data[i]);
            
        }
    }
    
    private void swap(int[] data, int a, int b){
        int tmp = data[a];
        data[a] = data[b];
        data[a] = tmp;
    }
}
