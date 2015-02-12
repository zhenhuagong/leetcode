package com.simongong;

/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.

思路：
1. 两个指针left, right，夹逼。当left < right时，left右移。反之right左移。
2. 维护3个变量： maxVolumn, maxLeft, maxRight。
正确性证明：
由于水的容量是有较小的那个边界决定的，因此某次迭代中，假设height[i] < height[j]，那么j 减小肯定不会使水的容量增大，只有i增加才有可能使水的容量增大。
但是会不会有这种可能：当前的i和 某个k (k > j)是最大容量?
这也是不可能的，因为按照我们的移动规则，既然右指针从k移动到了j，说明i的左边一定存在一个边界 m，使[m, k]的容量肯定大于[i, k]，所以[i,k]不可能是最大容量。

 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] testData1 = {0, 2, 4, 3, 1, 4, 6, 2};
        
        System.out.println(findMaxVolumn(testData1) == 16);
        
        int[] result = findMax(testData1);
        System.out.println(result[0] + ", " + result[1] + ", " + result[2]);
        System.out.println(result[0] == 16 && result[1] == 2 && result[2] == 6);
    }

    public static int findMaxVolumn(int[] data){
        int maxVolumn = 0;
        if(data == null || data.length == 0){
            return maxVolumn;
        }
        
        int left = 0, right = data.length - 1;
        while(left < right){
            int currentShort = data[left] < data[right] ? data[left] : data[right];
            int currentVolumn = currentShort * (right - left);
            maxVolumn = currentVolumn > maxVolumn ? currentVolumn : maxVolumn;
            if(data[left] < data[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxVolumn;
    }
    
    public static int[] findMax(int[] data){
        int[] result = {0, 0, 0}; // {max, left, right}
        if(data == null || data.length == 0){
            return result;
        }
        
        int left = 0, right = data.length - 1;
        int maxVolumn = 0, maxLeft = left, maxRight = right;
        while(left < right){
            if(data[left] < data[right]){
                int currentVolumn = data[left] * (right - left);
                if(currentVolumn > maxVolumn){
                    maxVolumn = currentVolumn;
                    maxLeft = left;
                    maxRight = right;
                }
                left++;
            }else if(data[left] > data[right]){
                int currentVolumn = data[right] * (right - left);
                if(currentVolumn > maxVolumn){
                    maxVolumn = currentVolumn;
                    maxLeft = left;
                    maxRight = right;
                }
                right--;
            }else{
                int currentVolumn = data[left] * (right - left);
                if(currentVolumn > maxVolumn){
                    maxVolumn = currentVolumn;
                    maxLeft = left;
                    maxRight = right;
                }
                left++;
                right--;
            }
        }
        result[0] = maxVolumn;
        result[1] = maxLeft;
        result[2] = maxRight;
        return result;
    }
}
