package com.simongong;

import java.util.ArrayList;

/*
Find K-th largest element in an array.

Note
You can swap elements in the array

Example
In array [9,3,2,4,8], the 3th largest element is 4

Challenge
O(n) time, O(1) space

思路：
这一题有很多解法：
1. quicksort partition，快排加pivot位置判断。O(n) + O(1)
2. k大小的堆。O(nlgk) + O(lgk)
3. 如果是整数，利用桶排序的思想，先扫一遍元素，求出min/max。
然后把[min, max]分为M份，再扫一遍，给对应的桶里的计数+1。完成之后由后往前统计所有桶里的元素个数，找到第k大元素所在的桶，和桶内第几大元素k'。
再次在桶内查找第k'大的元素。

根据需要和要求来选择。这里要求空间复杂度O(1)，所以选择第一种方法。另外就当复习快排了。

改进的快排：随机选pivot，当pivot的位置为k时返回。
这叫做Quicksort partition，可以达到O(n)的时间复杂度，并且不需要额外空间。

最坏情况下的时间复杂度：如果我们是这样的数组：1，2，3，4，5，然后又每一次取左边的当pivot，就会达到最坏的时间复杂度。也就是O(N2)

有一些解决方法：
以下来自维基，我们可以取3个数进行平均。
http://stackoverflow.com/questions/7559608/median-of-three-values-strategy
The easiest solution is to choose a random pivot, which yields almost certain linear time.
Deterministically, one can use median-of-3 pivot strategy (as in the quicksort), which yields linear performance on partially sorted data, as is common in the real world. 
However, contrived sequences can still cause worst-case complexity;
David Musser describes a "median-of-3 killer" sequence that allows an attack against that strategy, which was one motivation for his introselect algorithm.
 */
public class KthLargestElement {

    public int kthLargestElement(ArrayList<Integer> numbers, int k){
        if (numbers == null || k < 0 || numbers.size() < k) {
            return 0;
        }
        
        // note that the sorted sequence will be ascending
        // index of the kth largest elements is (length - k + 1)
        return getKth(numbers, numbers.size() - k + 1, 0, numbers.size() - 1);
    }
    
    private int getKth(ArrayList<Integer> numbers, int k, int left, int right){
        int pivot = numbers.get(right); // select right as pivot each time
        int start = left, end = right;
        while (true) {
            while (numbers.get(start) < pivot && start < right) {
                start++;
            }
            while (numbers.get(end) >= pivot && end > start) {
                end--;
            }
            if (start == end) { // break point is related to the conditions above
                break;
            }
            
            swap(numbers, start, end);
        }
        // now numbers is like x,x,start/end,xxx
        // start is the first element that is bigger than pivot
        swap(numbers, start, right);    // get pivot/numbers(right) positioned
        
        if (k == start + 1) {
            return pivot;
        }else if (k < start + 1) {   // kth will be someone in the left part
            return getKth(numbers, k, left, start - 1);
        }else {
            return getKth(numbers, k, end+1, right);
        }
    }
    
    private void swap(ArrayList<Integer> list, int a, int b){
        int tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}
