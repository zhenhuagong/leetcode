package com.simongong;

/*
 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

思路：
虽然不能再另外开辟非常数级的额外空间，但是可以在输入数组上就地进行swap操作。
步骤：
交换数组元素，使得数组中第i位存放数值(i+1)。
最后遍历数组，寻找第一个不符合此要求的元素，返回其下标。
整个过程需要遍历两次数组，复杂度为O(n)。
注意点：
1. 判断是否swap时要判目标值是不是相同（会有重复情况），否则会导致死循环。
2. 如果没找到，返回数组下标+1

 */
public class FirstMissingPositive {
    public static void main(String[] args){
        int[] testData1 = {1,2,0};
        int[] testData2 = {3,4,-1,1};
        System.out.println(findFirstMissing(testData1));
        System.out.println(findFirstMissing(testData2));
    }
    
    public static int findFirstMissing(int[] data){
        if(data == null){
            return 0;
        }
        
        for(int i = 0; i < data.length; i++){
            while(data[i] <= data.length && data[i] > 0 && data[data[i] - 1] != data[i]){
                swap(data, i, data[i] - 1);
            }
        }
        
        for(int i = 0; i < data.length; i++){
            if(data[i] != (i+1)){
                return (i+1);
            }
        }
        
        return (data.length+1);
    }
    
    public static void swap(int[] data, int i, int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
