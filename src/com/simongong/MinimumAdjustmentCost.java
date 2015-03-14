package com.simongong;

import java.util.ArrayList;

/*
Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]| 

注意
You can assume each number in the array is a positive integer and not greater than 100

样例
Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal. Return 2.

思路：
1. 乍一看挺复杂，但是条件中有两个条件我们可以利用：数组元素都是正数，且范围在[0, 100]之间。
那么尝试用递归来暴力破解：从数组第一个元素开始，每一位都尝试[1,100]次取值，后一位的合法取值跟前一位的取值相关。
对应实现：minimumAdjustRecursive(ArrayList<Integer> data, ArrayList<Integer> result, int target, int index)

2. 尝试改进。对暴力破解的改进有两种方式：找到计算结果的集合，缓存中间计算结果，避免重复计算；分析计算条件，及时剪枝，避免无谓的计算。
1)缓存中间计算。
用一个二维数组cost[i][j]，0<j<=100，表示将data[i]调整为j的cost。
对应实现：minimumAdjustRecCache(ArrayList<Integer> data, int target)

2)缓存DP中间结果，
用一个二维数组cost[i][j]，0<j<=101，表示将data[i]调整为j的最小cost。
初始值cost[0][j]=Math.abs(data[0] - j)
cost[i][j] = Math.min(cost[i-1][j-target, ..., j+target]) + Math.abs(data[i] - j)
也就是对于cost[i][j]，扫描data[i-1][1,...100]的最小值，然后加上data[i]自身调整到j的cost。
有了这样的一个cost数据之后，返回cost[len-1]数组中最小值问题就行了。
对应实现：minimumAdjustCache(ArrayList<Integer> data, int target)
 */
public class MinimumAdjustmentCost {

    public int minimumAdjustRecursive(ArrayList<Integer> data, ArrayList<Integer> result, int target, int index){
        int len = data.size();
        if (index >= len) {
            return 0;
        }
        
        int dif = 0, min = Integer.MIN_VALUE;
        for (int i = 1; i <= 100; i++) {
            // for the first element, it could be any one of [1, 100]
            if (index != 0 && Math.abs(i - result.get(i-1)) > target) {
                continue;
            }
            result.set(index, i);
            dif = Math.abs(i - data.get(index));
            dif += minimumAdjustRecursive(data, result, target, index+1);
            min = Math.max(min, dif);
            
            // finish calculate the (index+1)th in data
            // backtrace
            result.set(index, data.get(index));
        }
        
        return min;
    }
    
    public int minimumAdjustRecCache(ArrayList<Integer> data, int target){
        if (data == null || data.size() == 0) {
            return 0;
        }
        
        int len = data.size();
        int[][] cost = new int[len][100];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 100; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        
        return minimumAdjustRecCache(data, new ArrayList<>(data), target, 0, cost);
    }
    public int minimumAdjustRecCache(ArrayList<Integer> data, ArrayList<Integer> result, int target, int index, int[][] cost){
        int len = data.size();
        if (index >= len) {
            return 0;
        }
        
        int dif = 0, min = Integer.MIN_VALUE;
        for (int i = 1; i <= 100; i++) {
            if (index != 0 && Math.abs(i - result.get(i-1)) > target) {
                continue;
            }
            if (cost[index][i-1] != Integer.MAX_VALUE) {
                dif = cost[index][i-1];
                min = Math.min(min, dif);
                continue;
            }
            result.set(index, i);
            dif = Math.abs(i - data.get(index));
            dif += minimumAdjustRecursive(data, result, target, index+1);
            min = Math.max(min, dif);
            cost[index][i-1] = dif;
            
            // backtrace
            result.set(index, data.get(index));
        }
        
        return min;
    }
    
    public int minimumAdjustCache(ArrayList<Integer> data, int target){
        int size = data.size();
        if (data == null || size == 0) {
            return 0;
        }
        
        int[][] cost = new int[size][101];
        
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < 100; j++) {
                cost[i][j] = Integer.MAX_VALUE;
                if (i == 0) {   // for the first element
                    cost[i][j] = Math.abs(j - data.get(i));
                }else {
                    // for non-first element i, get minimum cost[i-1][k] 1<=k<=100
                    for (int k = 1; k <= 100; k++) {
                        if (Math.abs(j - k) > target) { // ignore undesired ones
                            continue;
                        }
                        int dif = Math.abs(j - data.get(i)) + cost[i-1][k];
                        cost[i][j] = Math.min(cost[i][j], dif);
                    }
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            result = Math.min(result, cost[size-1][i]);
        }
        
        return result;
    }
}
