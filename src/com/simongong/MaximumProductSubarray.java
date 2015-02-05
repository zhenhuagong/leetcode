package com.simongong;

/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

思路： DP问题。
因为有正负值好几种情况。所以我们计算当前节点最大值，最小值时，应该考虑前一位置的最大值，最大值几种情况。
（例如：如果当前为-2， 前一个位置最小值为-6，最大值为8，那么当前最大值应该是-2*-6 = 12）
对于以index位置结尾的连续子串来说，计算最大，最小值可以三种选择：
1. data[index] * 前一位置的最大值。
2. data[index] * 前一位置的最小值。
3. 丢弃前一位置的所有的值
4. 对这三项取最大值，最小值，就可以获得到当前index为止的子串的最大乘积，最小乘积。
 */
public class MaximumProductSubarray {

    public static void main(String[] args){
        int[] testData1 = {2,3,-2,4};
        System.out.println(maxProduct(testData1));
    }
    
    public static int maxProduct(int[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        int min = 1, max = 1;
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < data.length; i++){
            int n1 = max * data[i];
            int n2 = min * data[i];
            // Accumulate or drop previous
            max = Math.max(data[i], Math.max(n1, n2));
            min = Math.min(data[i], Math.min(n1, n2));
            
            result = Math.max(max, result);
        }
        return result;
    }
}
