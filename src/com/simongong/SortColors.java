package com.simongong;

/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

思路：
计数排序，扫两遍。
1. 扫一遍，统计每个数字出现的次数
2. 第二遍，按顺序放数字

用指针，扫一遍。
1. 想象排好序之后的数组，用一个指针指向中间1部分的第一个元素，一个指针指向中间1部分的下一个元素，剩余的部分左边是1，右边是2
2. 初始化left=0, right=data.length - 1。用一个指针cur遍历，每次对data[cur]的值进行判断，根据0、1、2三种情况来做不同处理
3. 在遍历的时候，碰到0就交换data[left]和data[cur]，递增left和cur；
4. 碰到1就什么都不干，递增cur；
5. 碰到2就交换data[cur]和data[right]，递减right，但不递增cur。因为data[right]换到cur的位置时，不一定是符合cur位置的要求（不是1），因此还需继续处理data[cur]
eg: 
  1, 0, 2, 1, 0, 2, 1
  L/c               R
  1, 0, 2, 1, 0, 2, 1  ->  swap(L,c), inc L/c
  L  c              R
  0, 1, 2, 1, 0, 2, 1  ->  swap(R,c), inc R
     L  c           R
  0, 1, 1, 1, 0, 2, 2
     L  c        R
  0, 1, 1, 1, 0, 2, 2
     L     c     R
  0, 1, 1, 1, 0, 2, 2  ->  swap(L,c), inc L/c
     L        c  R
  0, 0, 1, 1, 1, 2, 2  ->  swap(L,c), inc L/c
        L        c/R
 */
public class SortColors{

    public static void main(String[] main){
        Integer[] testData1 = {1,1,2,1,2,0,2,1,0,2};
        sortColorsCounting(testData1, 3);
        Utils.printArray("Sorted colors: ", testData1, ", ");
        Integer[] testData2 = {1,1,2,1,2,0,2,1,0,2};
        sortColorsPointer(testData2);
        Utils.printArray("Sorted colors: ", testData2, ", ");
    }
    
    // Can be easily applied to cases with more colors, two-pass required though,
    public static void sortColorsCounting(Integer[] data, int colors){
        if(data == null || data.length == 0){
            return;
        }
        // Count the occurrence of each color
        int[] countArray = new int[colors];
        for(int i = 0; i < data.length; i++){
            countArray[data[i]]++;
        }
        // Group the elements of array in color
        for(int i = 0; i < data.length; i++){
            if (countArray[0] > 0) {
                data[i] = 0;
                countArray[0]--;
            }else if (countArray[1] > 0) {
                data[i] = 1;
                countArray[1]--;
            }else {
                data[i] = 2;
            }
        }
    }
    
    // One-pass algorithm, only applied to 3 colors case.
    public static void sortColorsPointer(Integer[] data){
        if(data == null || data.length == 0){
            return;
        }
        
        int left = 0, right = data.length - 1;
        int cur = 0;
        while(cur <= right){
            switch (data[cur]) {
                case 0:
                    Utils.swap(data, left, cur);
                    left++;
                    cur++;
                    break;
                case 1:
                    cur++;
                    break;
                case 2:
                    Utils.swap(data, cur, right);
                    right--;
                    break;  // don't increment cur because we may get 0/1 from right which requires further process
                default:
                    cur++;
                    break;
            }
        }
    }
}
