package com.simongong;

/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

难

思路：
跟原版问题的处理一样，采用二分查找。 
差别在于，在while循环中，增加一个条件判断，即对data[mid] == data[left] || data[mid] == data[right]的处理
1. 简化地想，当data[mid] == data[left]时，说明此时left对于我们得出结果的目的来说是“无效的”，直接丢弃它，left++
2. 而对于data[mid] == data[right]的情况，前提是data[mid] < data[left]，此时data[left]也是无用的，因此也left++，从而简化了程序
3. 每次进入循环，我们都要判断A.left < A.right，原因是，前面我们丢弃一些数字时，有可能造成余下的数组是有序的，这时应直接返回A.left! 否则的话 我们可能会丢掉解。
举例: 10 1 10 10 如果我们丢弃了最左边的10，则1 10 10 是有序的
 */
public class FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] testData1 = {3,4,5,6,7,8,0,1,2};
        int[] testData2 = {6,7,8,0,1,2,3,4,5};
        int[] testData3 = {0,1,2,3,4,5,6,7,8};
        System.out.println(findMinimum(testData1));
        System.out.println(findMinimum(testData2));
        System.out.println(findMinimum(testData3));
    }

    public static int findMinimum(int[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        int left = 0, right = data.length - 1;
        
        while(left < right - 1){
            if(data[left] < data [right]){
                return data[left];
            }
            int mid = left + (right - left) / 2;
            if(data[mid] > data[left]){ // left is sorted, drop it
                left = mid;
            }else if(data[mid] < data[right]){  // right is sorted, drop it
                right = mid;
            }else{  // neither left nor right is sorted, increment left
                left++;
            }
        }
        
        return Math.min(data[left], data[right]);
    }
}
