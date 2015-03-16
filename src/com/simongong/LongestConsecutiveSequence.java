package com.simongong;

import java.util.HashSet;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

思路：
用排序的话，得要O(NlgN)的复杂度，也就是不能在遍历的基础上还有元素间的比较（这会导致至少O(NlgN)的复杂度）。
因此，目测得要用空间来换取时间，扫多遍。
直观的看，如果用hash方式，无法预估有效的hash函数和hash表空间大小。因此不能用值映射hash地址的方式。
先定义一个全局的maxConsecutive。
换个思路，先把元素都丢到一个集合里，然后逐个取elem，取的过程中再前后试探elem的邻居是否也在集合中，在的话就累加临时的consecutive
然后每步都更新maxConsecutive，遍历结束后就得到了结果。
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        Integer[] testData = {100, 98, 45, 43, 12, 0, 44};
        Utils.printArray("Original array:", testData, ", ");
        System.out.println("Longest Consecutive Sequence: " + findLongestConsecutive(testData));
    }

    public static int findLongestConsecutive(Integer[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        HashSet<Integer> dataSet = new HashSet<Integer>();
        for(int i : data){
            dataSet.add(i);
        }
        
        int maxConsecutive = 0;
        for(int i : data){
            // we should use remove() here to avoid unnecessary check
            if(dataSet.remove(i)){  // i could have been removed beforehand
                int tmpConsecutive = 1;
                int tmpNeighbor = i - 1;
                while(dataSet.contains(tmpNeighbor)){
                    dataSet.remove(tmpNeighbor);
                    tmpConsecutive++;
                    tmpNeighbor--;
                }
                
                tmpNeighbor = i + 1;
                while(dataSet.contains(tmpNeighbor)){
                    dataSet.remove(tmpNeighbor);
                    tmpConsecutive++;
                    tmpNeighbor++;
                }
                
                maxConsecutive = Math.max(maxConsecutive, tmpConsecutive);
            }
        }
        return maxConsecutive;
    }
}
