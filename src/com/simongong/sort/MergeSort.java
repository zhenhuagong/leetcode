package com.simongong.sort;

/*
思路：
不断二分数组，然后从底部的一个元素的子数组开始，两两合并，直到生成最后的排序数组。
逻辑分为两个主体：
1. 递归二分的逻辑
有两个递归返回条件：arr.length=1/0；本层调用中的两个子数组都已计算完毕，返回其合并结果
2. 合并两个排好序子数组的逻辑。
 */
public class MergeSort {

    public int[] mergeSort(int[] data){
        if (data == null) {
            return null;
        }
        
        if (data.length <= 1) {
            return data;
        }
        
        // divide into two arrays
        int[] left = new int[data.length / 2];
        int[] right = new int[data.length - data.length / 2];
        
        System.arraycopy(data, 0, left, 0, data.length/2);
        System.arraycopy(data, data.length/2, right, 0, data.length - data.length/2);
        
        // recursively mergeSort to get left and right sorted
        left = mergeSort(left);
        right = mergeSort(right);
        
        return merge(left, right);
    }
    
    // premis: left and right are sorted and not null
    private int[] merge(int[] left, int[]right){
        int len = left.length + right.length;
        
        int[] result = new int[len];
        
        int leftP = 0, rightP = 0, cur = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] < right[rightP]) {
                result[cur++] = left[leftP++];
            }else {
                result[cur++] = right[rightP++];
            }
        }
        if (leftP > left.length) {
            while (rightP < right.length) {
                result[cur++] = right[rightP];
            }
        }else {
            while (leftP < left.length) {
                result[cur++] = left[leftP];
            }
        }
        return result;
    }
}
