package com.simongong;
/*
There are two sorted arrays A and B of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

median定义：
计算有限个数的数据的中位数的方法是：把所有的同类数据按照大小的顺序排列。
如果数据的个数是奇数，则中间那个数据就是这群数据的中位数；如果数据的个数是偶数，则中间那2个数据的算术平均值就是这群数据的中位数。
因此，在计算中位数Median时候，需要根据奇偶分类讨论。

思路：
问题等价于，在排好的数组中，在目标数之前，一共有k个比目标更小的数。
那么将k分成两份，一份在A的前端，一份在B的前端。这里其实将k怎么分配是一个可以讨论的问题，但是平分k可以得到平均最快的效果。后面会讨论不同的分配方式。
如果a[k/2 - 1] == b[k/2 - 1]，那么a和b合并之后的数组中，a[k/2 - 1]的元素正好在第k的位置。
我们就要寻找a[k/2 - 1] == b[k/2 - 1]这个值。
因此，寻找一个unioned sorted array中的第k大（从1开始数）的数，等价于寻找并判断两个sorted array中第k/2（从1开始数）大的数。
第k/2（从1开始数）大的数就是元素a[k/2 - 1]，因此我们需要判断a[k/2-1]和b[k/2-1]的大小：
1. 如果a[k/2-1]==b[k/2-1]，那么这个数就是两个数组中第k大的数。
2. 如果a[k/2-1]<b[k/2-1], 那么说明a[0]到a[k/2-1]都不可能是第k大的数，所以需要舍弃这一块，继续从a[k/2]到a[A.length-1]继续找。当然，因为这里舍弃了a[0]到a[k/2-1]这k/2个数，那么第k大也就变成了，第k-k/2个大的数了。
3. 如果 a[k/2-1]>b[k/2-1]，就做之前对称的操作就好。
 这样整个问题就迎刃而解了。

实现：
为简洁，使用DFS来实现。
每次对a，b取前k/2个元素。关键点在于a的元素不够k/2时，怎么处理。有两种方法：
1). 直接丢弃b前k/2. 反之b的元素不够k/2时，同样处理。 对应：dfs(int[] a, int[] b, int aStart, int bStart, int k)
证明： 反证法。假设第K大在b的前k/2中的m位置，那么a必然拥有前k中的k -(m+1)个元素，而m <= k/2 - 1，因此m+1 <= k/2  , k - (m+1) > k/2与a的元素不够k/2矛盾。
2). 对于k，分(aEnd - aStart + 1)个给a，剩余的给b，对应：findKth(int[] a, int aStart, int aEnd, int[] b, int bStart, int bEnd, int k)
也就是把a全部加入比较，b中的前(k - (aEnd - aStart))个元素加入比较，组合起一个最小的k个元素集合。证明方式同上，反证法。
注意,由于对每个数组的分半操作采取：
int breakpointA = Math.min(k/2,m);
int breakpointB = k - partA; 
 为了能保证上面的分半操作正确，需要保证a数组的长度小于b数组的长度。

递归的跳出条件包括三种：
1. aEnd - aStart == 0，也就是a数组全部被丢弃了，这时问题就转化为求b数组中第k大元素，直接返回b[k-1]。
2. k == 1，也就是找最小的元素，只用比较a[aStart]和b[bStart]，返回较小值即可。
3. 发现a[k/2-1]==b[k/2-1]，那么返回a[k/2-1]

 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8, 15, 32};
        int[] b = {1, 3, 4, 6, 10, 12, 16};
        System.out.println(findMedian(a, b) == 6);
    }

    public static double findMedian(int[] a, int[] b){
        // one of the two arrays is empty
        if(a == null || a.length == 0){
            return getMid(b, 0, b.length);
        }
        if(b == null || b.length == 0){
            return getMid(a, 0, a.length);
        }
        
        int len = a.length + b.length;
        // when total length is even, get the mean of the 2 mid
        if(len % 2 == 0){
            // the (len/2 + 1)th element equals the element of a[len/2]
            //return (double)(dfs(a, b, 0, 0, len / 2) + dfs(a, b, 0, 0, len / 2 + 1)) / 2.0;
            return (double)(findKth(a, 0, a.length - 1, b, 0, b.length - 1, len / 2) + findKth(a, 0, a.length - 1, b, 0, b.length - 1, len / 2 + 1)) / 2;
        }else{
            //return dfs(a, b, 0, 0, len / 2 + 1);
            return (double)findKth(a, 0, a.length - 1, b, 0, b.length - 1, len / 2 + 1);
        }
    }
    
    /*
     * Find a[k/2 - 1] == b[k/2 -1]
     * by keep dropping k/2 elements recursively.
     */
    private static double findKth(int[] a, int aStart, int aEnd, int[] b, int bStart, int bEnd, int k){
        int m = aEnd - aStart + 1;
        int n = bEnd - bStart + 1;
        
        // make sure `b` is longer than `a`
        if(m > n){
            return findKth(b, bStart, bEnd, a, aStart, aEnd, k);
        }
        
        // break point #1
        // all of elements in a has been dropped, now find out kth in b
        if(m == 0){
            return b[k - 1];
        }
        
        // break point #2
        // find the minimum element in sorted a and b
        if(k == 1){
            return Math.min(a[aStart], b[bStart]);
        }
        
        int breakpointA = Math.min(k/2, m);   // make sure (k - breakpointA) > 0
        int brekpointB = k - breakpointA;  // KEY point! the total number of candidates for dropping in a and b equals k
        if(a[aStart + breakpointA - 1] < b[bStart + brekpointB - 1]){  // dropping k/2(or all) elements at the left side of a
            return findKth(a, aStart + breakpointA, aEnd, b, bStart, bEnd, k - breakpointA);
        }else if(a[aStart + breakpointA - 1] > b[bStart + brekpointB - 1]){    // dropping k/2 elements at the left side of b
            return findKth(a, aStart, aEnd, b, bStart + brekpointB, bEnd, k - brekpointB);
        }else{  // get the kth element
            return a[aStart + breakpointA - 1];
        }
    }
    
    /*
每次对A，B取前k/2个元素。有以下这些情况：
1). A的元素不够k/2. 则我们可以丢弃B前k/2. 反之亦然
证明： 反证法。假设第K大在B的前k/2中的m位置，那么A必然拥有前k中的k -(m+1)个元素，而m <= k/2 - 1，因此m+1 <= k/2  , k - (m+1) > k/2与A的元素不够k/2矛盾。
2). A[mid] < B[mid] (mid是k/2 -1索引处的元素）。
这种情况下，我们可以丢弃A前k/2。
     */
    private static double dfs(int[] a, int[] b, int aStart, int bStart, int k){
        if(aStart >= a.length){ // we have dropped the whole a, kth element is in b
            return b[bStart + k - 1];
        }else if(bStart >= b.length){   // we have dropped the whole b, kth element is in a
            return a[aStart + k - 1];
        }
        
        // get the first element
        if(k == 1){
            return Math.min(a[aStart], b[bStart]);
        }
        
        int mid = k / 2 - 1;
        // first, make sure that a[aStart + mid]/b[bStart + mid] is valid
        // we can drop k/2 elements at the left side
        if(aStart + mid >= a.length){   // drop k/2 elements at the left side of b
            return dfs(a, b, aStart, bStart + k / 2, k - k / 2);
        }else if(bStart + mid >= b.length){  // drop k/2 elements at the left side of a
            return dfs(a, b, aStart + k / 2, bStart, k - k / 2);
        }else if (a[aStart + mid] > b[bStart + mid]){   // drop k/2 elements at the left side of b
            return dfs(a, b, aStart, bStart + k / 2, k - k / 2);
        }else if (a[aStart + mid] < b[bStart + mid]){   // drop k/2 elements at the left side of a
            return dfs(a, b, aStart + k / 2, bStart, k - k / 2);
        }else{
            return a[aStart + mid];
//            if(k % 2 == 0){
//                return a[aStart + mid];
//            }
            // when a[aStart + mid] == b[bStart + mid] && k is odd, drop both sides
            //return dfs(a, b, aStart + k / 2, bStart + k / 2, 1);
        }
    }
    
    /*
     * @param
     * start,  index of start element
     * end, (index + 1) of end element
     */
    private static int getMid(int[] a, int start, int end){
        if((end - start) % 2 == 0){
            return a[(end - start)/2 - 1] + (a[end - start/2] - a[end - start/2 - 1]) / 2;
        }else{
            return a[end - start/2];
        }
    }
}
