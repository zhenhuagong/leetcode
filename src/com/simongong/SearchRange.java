package com.simongong;

/*
Given a sorted array of integers, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

思路：
开始时考虑头尾两个指针相向遍历，但这样做的复杂度是O(N)
要O(logN)的复杂度，肯定是要二分的。

使用改进的二分查找法。
终止条件是：left < right - 1 这样结束的时候，会有2个值供我们判断。这样做的最大的好处是，不用处理各种越界问题。

二分法模板：
1. 先找左边界。当mid == target,将right移动到mid，继续查找左边界。
 最后如果没有找到target,退出
2. 再找右边界。当mid == target,将left移动到mid，继续查找右边界。
 最后如果没有找到target,退出

 */
public class SearchRange {

    public static void main(String[] args) {
        int[] testData = {5, 7, 7, 8, 8, 10};
        int target = 8;
        
        int[] result = searchRange(target, testData);
        System.out.println(result[0] + ", " + result[1]);
    }

    private static int[] searchRange(int target, int[] data) {
        int[] result = {-1, -1};
        
        if(data == null || data.length == 0){
            return result;
        }
        
        // Keep divide by 2 until two elements left
        // where the left target is
        int left = 0, right = data.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(target == data[mid]){
                right = mid;
            }else if(target > data[mid]){   // go with right half, set left
                left = mid;
            }else{
                right = mid;
            }
        }
        
        if(data[left] == target){
            result[0] = left;
        }else if(data[right] == target){
            result[0] = right;
        }else{
            return result;
        }
        
        left = 0;
        right = data.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(target == data[mid]){
                left = mid;
            }else if(target > data[mid]){
                left = mid;
            }else{
                right = mid;
            }
        }
        
        if(data[right] == target){
            result[1] = right;
        }else if(data[left] == target){
            result[1] = left;
        }else{
            return result;
        }
        
        return result;
    }

}
