package com.simongong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/*
Find the most k frequent numbers

 * Input: int[] A = {1, 1, 2, 3, 4, 5, 2}; k = 3
 * return the highest frequency numbers.
 * return: [1, 2, 3] or [1, 2, 4] or [1, 2, 5]

思路：
1. 找最大的k个数，自然使用大小为k的堆
2. 先统计出现频率，使用hashmap
3. 扫hashmap维护堆的时候，有个小技巧。把堆大小设置为k+1，这样可以每次先拉出一个频率最小值，再添加一个值，全部完成后，频率最大的k个数字会留在队列中。
如果建立大小为k的堆，那需要每次弹出堆顶元素，比较，再压入新元素。而设置成k+1大小的话，就免除了比较操作，让堆自己调整。

时间复杂度：NlogK, 如果K比较小的时候，就相当于N了。
 */
public class FindFirstKFrequencyNumbers {

    public ArrayList<Integer> findMostKFrequent(int[] data, int k){
        if (data == null || data.length == 0) {
            return null;
        }
        
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int num : data) {
            if (frequency.containsKey(num)) {
                frequency.put(num, frequency.get(num) + 1);
            }else {
                frequency.put(num, 1);
            }
        }
        
        PriorityQueue<Entry<Integer, Integer>> queue = new PriorityQueue<>(k+1, new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        
        for(Entry<Integer, Integer> entry: frequency.entrySet()){
            if (queue.size() == k + 1) {    // poll the one with currently lowest frequency
                queue.poll();
            }
            queue.offer(entry);
        }
        
        ArrayList<Integer> results = new ArrayList<>();
        queue.poll();   // removed the (k+1)th frequency element
        while (!queue.isEmpty()) {
            results.add(queue.poll().getKey());
        }
        
        return results;
    }
}
