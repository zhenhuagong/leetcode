package com.simongong;

import java.util.ArrayList;

/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

思路：
每次有条件地移动一步，计算一个最值，走完之后得到最终的最值，找到一条最佳路径，典型的动态规划。
而且从条件来看，当前一步依赖于下一步的计算结果，因此需要自底向上求解，保存计算过的值。
递推公式是： dp[i][j] = dp[i+1][j] + dp[i+1][j+1] ，当前这个点的最小值，由他下面那一行临近的2个点的最小值与当前点的值相加得到。
由于是三角形，且历史数据只在计算最小值时应用一次，所以无需建立二维数组，每次更新1维数组值，数组第i个元素里存的就是从底部开始第1步最终结果。

 */
public class Triangle {

    public int triangleMinSum(ArrayList<ArrayList<Integer>> triangle){
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int rows = triangle.size();
        if (rows == 1) {
            return triangle.get(0).get(0);  // only one element in this row
        }
        
        int[] cache = new int[rows];
        ArrayList<Integer> row = triangle.get(rows - 1);
        for (int i = 0; i < row.size(); i++) {  // initial cache with bottom row
            cache[i] = row.get(i);
        }
        // dp from bottom
        for (int i = rows - 1; i >= 0; i--) {
            row = triangle.get(i);
            int col = row.size();
            for(int j = 0; j < col; j++){
                // calculte elements in cache based on the old cache
                cache[j] = Math.min(cache[j], cache[j+1]) + row.get(j);
            }
        }
        return cache[0];
    }
}
