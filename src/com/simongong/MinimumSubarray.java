package com.simongong;

/*
原题链接： http://lintcode.com/zh-cn/problem/minimum-subarray/#

Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

注意
The subarray should contain at least one integer.

样例
For [1, -1, -2, 1], return -3

思路：
跟求最大和子数组一样： 不能与负数结合！

把数组整体乘以-1，求出最大和max，那么-max就是求最小和了。
 */
public class MinimumSubarray {

    public int minSumSubArray(int[] data){
        if(data == null || data.length == 0){
            return Integer.MIN_VALUE;
        }
        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < data.length; i++){
            if(sum < 0){
                sum = -data[i];
            }else{
                sum += -data[i];
            }
            
            max = Math.max(max, sum);
        }
        return -max;
    }
}
