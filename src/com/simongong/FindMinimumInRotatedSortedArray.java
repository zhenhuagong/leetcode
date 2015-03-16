/**
 * 
 */
package com.simongong;

/**
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.

You may assume no duplicate exists in the array.

解析：
描述中的数组就相当是分块有序的数组，寻找最小元素。
一维数组的查找，首先尝试复杂度为O(lgN)的算法，二分。
因为是升序序列，因此每次mid跟right比较。
当mid比右边大的时候，右边结构为[mid,...max,min,...,right]。最小元素肯定在右边，[left,mid-1]就可以丢弃了
反之，最小元素肯定在左边，[mid+1, right]就可以丢弃了

如果先判断[left, right]是否已经有序，否则判断left与mid的关系，丢掉已经排序的那边。
left > mid，右边是升序，丢弃
left < mid，左边是升序，丢弃

实现：
重点是在二分查找时，是否完整，对边界条件的处理是否健壮。（参考编程之美3.11节）
1. 二分查找模板（简洁）
2. 二分查找基础上，增加while中的判断（略繁，但思路直观，可以接受）
3. 预先判断是否sorted，再使用二分查找（思路清晰，程序简洁，推荐）

 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] testData1 = {3,4,5,6,7,8,0,1,2};
        int[] testData2 = {6,7,8,0,1,2,3,4,5};
        int[] testData3 = {0,1,2,3,4,5,6,7,8};
        System.out.println(findMinimimDivideTemplate(testData1));
        System.out.println(findMinimumDivideMoreCondition(testData1));
        System.out.println(findMinimumDivideOnlyNeccessary(testData1));
        System.out.println(findMinimimDivideTemplate(testData2));
        System.out.println(findMinimumDivideMoreCondition(testData2));
        System.out.println(findMinimumDivideOnlyNeccessary(testData2));
        System.out.println(findMinimimDivideTemplate(testData3));
        System.out.println(findMinimumDivideMoreCondition(testData3));
        System.out.println(findMinimumDivideOnlyNeccessary(testData3));
    }

    // As always, use divide-by-2 template to avoid complicated boundary cases hanlding
    // 1. while(left < right -1){ int mid = left + (right - left) / 2;}
    // 2. if(data[mid] > data[left]){ left = mid; } else { right = mid; }
    // finally, compare the remaining data[left]] and data[right] to get your desired result
    public static int findMinimimDivideTemplate(int[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        int left = 0, right = data.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(data[mid] < data[right]){
                right = mid;
            }else{
                left = mid;
            }
        }
        return Math.min(data[left], data[right]);
    }
    
    public static int findMinimumDivideMoreCondition(int[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        if(data.length == 1){
            return data[0];
        }else if(data.length == 2){
            return Math.min(data[0], data[1]);
        }
        
        int left = 0, right = data.length - 1;
        while(left < right -1){
            int mid = left + (right - left) / 2;
            if(data[mid] < data[right] && data[mid] > data[left]){  // [left, right] is sorted
                return data[0];
            }
            
            if(data[mid] > data[left]){ // left side is sorted, drop it
                left = mid;
            }else{  // right side is sorted, drop it
                right = mid;
            }
        }
        // because original data is sorted as ascend
        // so the minimum element of a sorted one is at right
        return data[right];
    }
    
    public static int findMinimumDivideOnlyNeccessary(int[] data){
        if(data == null || data.length == 0){
            return 0;
        }
        
        int left = 0, right = data.length - 1;
        if(data[left] < data[right]){
            return data[left];
        }
        
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(data[mid] < data[right]){
                right = mid;
            }else{
                left = mid;
            }
        }
        return Math.min(data[left], data[right]);
    }
}
