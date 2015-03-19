package com.simongong;
/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle

思路：
遍历数组，记录当前的cur_max，当扫到data[i]时，我得做判断：
1. 累加前面的cur_max，跟cur_max结合
2. 自立门户开始算cur_max，保持单身
如果cur_max已经是负数的话，data[i]跟cur_max累加，只会使data[i]变得更小。
而data[i]的底线是最小得是自己。于是data[i]决定先看看cur_max的大小，再做决定。
基本思路跟社会实践类似，就是妹子找对象，先看对方存款。宁可单身也不找有负债的穷逼，要找只找有存款的。

然后每次更新cur_max的时候，更新全局的max

这道题是经典的问题，是1977布朗的一个教授提出来的。
http://en.wikipedia.org/wiki/Maximum_subarray_problem

有两种经典解法：
1. Kadane算法，算法复杂度O(n)
就是上面讨论的思路
2. 另外一个是分治法：算法复杂度为O(nlogn)
类似于求树的最大sumPath： 
1. 求根节点左右子树的最大sumPath
2. 求穿过根节点的最大sumPath （只是这个求法与树的那个求法不一样）
3. 返回三者的较大值
 */
public class MaximumSubarray {

    public int maxSubarray(int[] data){
        int max = Integer.MIN_VALUE;
        int cur_max = 0;
        for(int i = 0; i < data.length; i++){
            if(cur_max < 0){
                cur_max = data[i];
            }else{
                cur_max += data[i];
            }
            max = Math.max(cur_max, max);
        }
        return max;
    }
    
    public int maxSubDivide(int[] data){
        return divide(data, 0, data.length - 1);
    }
    
    private int divide(int[] data, int low, int high){
        if(low == high){    // when size of data is 1 
            return data[low];
        }
        if(low == high - 1){    // when size of data is 2
            return Math.max(data[low] + data[high], Math.max(data[low], data[high]));
        }
        
        // else, divide data
        int mid = low + (high - low) / 2;
        int lmax = divide(data, low, mid - 1);  // get max of left side
        int hmax = divide(data, mid, high); // get max of right side
        
        // now we will calculate the max of an array that goes through mid 
        int mmax = data[mid];
        int tmp = mmax;
        for(int i = mid - 1; i >= low; i--){    // try going left from mid-1
            tmp += data[i];
            mmax = tmp > mmax ? tmp : mmax;
        }
        tmp = mmax;
        for(int i = mid + 1; i <= high; i++){   // try going right from mid+1
            tmp += data[i];
            mmax = tmp > mmax ? tmp : mmax;
        }
        
        return Math.max(mmax, Math.max(lmax, hmax));
    }
}
