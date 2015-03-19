package com.simongong;

import java.util.HashMap;

/*
Given an integer array, find a subarray where the sum of numbers is zero.
Your code should return the index of the first number and the index of the last number.

Excample:
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

思路：
跟字符转匹配KMP算法类似，主要是利用已经扫过的元素信息，判断在匹配失败时近可能优化的下一次扫描的起点。
因此，关键是生成类似next[i]数组。

我们在这里使用<sum, index>，表示从0累加到index处的sum的值。当某两个index拥有相同的sum时，它们之间的元素的累加和就为0. 比如
data: -3  1  2 -3  4
sum : -3 -2  0 -3  1
index: 0  1  2  3  4
可以看到0和3的下标位置对应的sum是相等的，说明[0, 3]和[1, 3]之间的元素的累加和为0.

实现的时候，为了判断简单，使用HashMap来记录<sum, index>，可以达到O(1)的判断速度。
 */
public class SubarraySum {

    public int[] findSub(int[] data){
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        map.put(sum, -1);   // set a start point
        for(int i = 0; i < data.length; i++){
            sum += data[i];
            if(map.containsKey(sum)){
                result[0] = map.get(sum) + 1;
                result[1] = i;
                return result;
            }
            
            map.put(sum, i);
        }
        
        return result;
    }
}
