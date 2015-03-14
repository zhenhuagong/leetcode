package com.simongong.sort;

/*
思路：
1. 每次按某种规则制定一个欲排序的元素pivot
2. 以pivot为基准，把数组划分两半。其中左边元素都比pivot小，右边都比pivot大，这样pivot的最终位置就确定了
3. 继续对上面的左右两半应用前两步
4. 第i次处理能确定2^i个元素的最终位置，所以时间复杂度为O(nlgn)
5. 最坏条件是在逆序情况下，退化成选择排序。
6. 由于关键的交换操作的操作对象是非相邻的元素，所以会打乱原有相对位置，是不稳定的。
 */
public class QuickSort {

    public void quickSort(int[] arr){
        quickSortRecursive(arr, 0, arr.length - 1);
    }
    
    private void quickSortRecursive(int[] arr, int low, int high){
        if (arr == null || low > high) {
            return;
        }
        
        int pivot = partition(arr, low, high, arr[high]);   // arr[high] is finally sorted at pivot
        quickSortRecursive(arr, low, pivot-1);  // recursively process the left side
        quickSortRecursive(arr, pivot+1, high); // recursively process the right side
    }
    
    // partition the array and return the new pivot position
    private int partition(int[] arr, int low, int high, int pivot){
        int l = low - 1;
        int r = high;
        
        // keep finding a element in the left which is bigger than pivot
        // and a element in the right which is less than pivot
        // then swap them until l > r
        
        /*
        example: let 6 to be the pivot.

        (1) At the beginning:
        3 4 6 1 7 8 6
        l           r
         
        (2) After the first while loop:
        3 4 6 1 7 8 6 
            l r  

        (3) swap them, then continue to move i and j:
        3 4 1 6 7 8 6 
            l r                 

        (3) swap them, then continue to move i and j:
        3 4 1 6 7 8 6 
              l     pivot
              r
        (4) swap the left and the pivot.
        3 4 1 6 7 8 6 
              l     pivot           
     */
        
        while(true){
            while(arr[++l] < pivot);
            while(r > l && arr[--r] > pivot);
            
            // all elements are rightly positioned already
            if (r <= l) {
                break;
            }
            
            swap(arr, r, l);
        }
        
        // at this point, l points to the first element that is bigger than the pivot
        swap(arr, l, high);
        return l;
    }
    
    private void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
