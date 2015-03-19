package com.simongong;

/*
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

注意
You are not necessary to keep the original order or positive integers or negative integers.

样例
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other legal answer.

挑战
Do it in-place and without extra memory.

思路：
根据提示，我们可以使用基于非相邻元素的交换的方法。
再根据存储空间的要求，排除了归并方式。
为了提前知道会扫描到正数还是负数，借用“对数组先排序再查找”的思路，我们先把数组分堆。

1. 先用partition把数组分成左边负数，右边正数，返回pivot位置；
2. 如果负数多，把多余的负数放到数组尾部去。保证待交换的部分，正负数个数相等；
3. 两个指针向向扫: left, right，交换，然后前进两步。直到left > right。

还有一种思路：
1. 扫一次确定是正数多还是负数多
2. 负数多，把负数放在最后； 正数多，把右边的负数移动到左边
2. 把奇数索引的所有的数字进行partition，如果是正数多，把正数放在后面，否则负数放在后面。
3. 令Index 1 = 奇数列，index 2 = 偶数列，扫描一次，遇到不符合正负条件的数字进行交换即可
 */
public class InterleavingPositiveAndNegativeNumbers {

    public int[] interleaveNumbers(int[] data){
        if(data == null || data.length == 0){
            return data;
        }
        
        int negCounts = partition(data, 0);
        int posCounts = data.length - negCounts;
        
        int left = -1, right = data.length - 1;
        int less = Math.min(negCounts, posCounts);
        int diff = Math.abs(posCounts - negCounts);
        
        // -1 -1 -1 -2 -2 2 2 2 -> -1 -1 -1 2 2 2 -2 -2 -> -1 2 -1 2 -1 2 -2 -2
        // -1 -1 -1 2 2 2 2 2 -> -1 -1 -1 2 2 2 2 2 -> 2 -1 2 -1 2 -1 2 2
        if(negCounts > posCounts){  // put diff of negative numbers to the end
            int count = diff;
            int l = less;
            int r = data.length - 1;
            while(count > 0){
                swap(data, l, r);
                l++;
                r--;
                count--;
            }
            left = -1;  // make negative first in final sequece
            right = data.length - diff;
        }else{
            left = -2;  // make positive first in final sequece
            right = data.length - diff + 1;
        }
        
        while(true){
            left += 2;
            right -= 2;
            
            if(left >= less){
                break;
            }
            swap(data, left, right);
        }
        return data;
    }
    
    private int partition(int[] data, int pivot){
        int left = 0, right = data.length - 1;
        while(true){
            while(left < (data.length - 1) && data[left] < pivot){
                left++;
            }
            while(left < right && data[right] > pivot){
                right--;
            }
            if(left > right){
                break;
            }
            swap(data, left, right);
        }
        return left;    // not left points to the first element that is bigger than pivot
    }
    
    private void swap(int[] data, int a, int b){
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
