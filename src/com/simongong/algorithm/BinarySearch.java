package com.simongong.algorithm;

public class BinarySearch {

    public int search(int[] data, int target){
        if(data == null || data.length == 0){
            return -1;
        }
        
        int left = 0, right = data.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if (data[mid] == target) {
                return mid;
            }else if(data[mid] < target){
                left = mid;
            }else {
                right = mid;
            }
        }
        return -1;
    }
}
