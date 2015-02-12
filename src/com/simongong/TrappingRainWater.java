package com.simongong;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

思路：
1. 看上去是求一个个子区间内的蓄水量然后求和，但每个子区间的划分和区间内的蓄水量求值都比较繁琐。
可以化繁求简，看到其实存储的水都是在墙顶的，类似于我们是在修补木桶的边。而能修补多少，取决于左右墙顶的。
因此，对于某条墙data[i]，我们需要求出
i. 从左边到data[i]最高的墙left
ii. 从右边到data[i]最高的墙right
iii. 取left与right的较小值作为height[i]，data[i]墙顶上能存的水就是height[i] - data[i]
最后对每条墙顶的水计算和。

 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] testData = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(findTrappingWater(testData) == 6);
    }

    public static int findTrappingWater(int[] data){
        int sum = 0;
        if(data == null || data.length == 0){
            return sum;
        }
        
        int[] height = new int[data.length];
        for(int i = 0; i < data.length; i++){
            int left = 0, right = data.length - 1;
            int leftHeight = 0, rightHeight = 0;
            while(left < i){
                leftHeight = data[left] > leftHeight ? data[left] : leftHeight;
                left++;
            }
            while(right > i){
                rightHeight = data[right] > rightHeight ? data[right] : rightHeight;
                right--;
            }
            height[i] = Math.min(leftHeight, rightHeight);
        }
        
        for(int i = 0; i < data.length; i++){
            int trapping = height[i] - data[i];
            sum += (trapping > 0 ? trapping : 0);
        }
        return sum;
    }
}
