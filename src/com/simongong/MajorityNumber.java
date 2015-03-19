package com.simongong;

/*
原题链接：http://lintcode.com/en/problem/majority-number/#

Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
For [1, 1, 1, 1, 2, 2, 2], return 1

Challenge
O(n) time and O(1) space

思路：
1. 对当前majority number累计计数cnt，出现不同数字时cnt--；当cnt==0时，更新majority number
举个直观的例子就好比，不断对某个议案投票，如果有人有别的议案，则将前面认为的议案的cnt减1，减到0换一个议案。
投票完成后，要对majority number进行检查，以排除不存在majority number的情况。如 1，2，3，4这样的数列，是没有majory number的。
 */
public class MajorityNumber {

    public int getMajority(int[] data){
        if(data == null || data.length == 0){
            return -1;
        }
        
        int major = data[0];
        int count = 1;
        for(int i = 1; i < data.length; i++){
            if(count == 0){
                major = data[i];
                count = 1;
            }else if(data[i] != major){
                count--;
            }else{
                count++;
            }
        }
        return major;
    }
}
