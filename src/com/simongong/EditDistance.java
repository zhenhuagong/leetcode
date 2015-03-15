package com.simongong;

/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

思路：
分析题目之后，发现适合用动态规划。先定义dp函数。
观察题目，社两个字符串s=s1s2...sn, r=r1r2...rn
定义dp[i][j] 为s1...si与r1...rj的最小编辑距离，我们来推导一下递推公式。看最后一位
S:   12...i
R:12......j
分析s[i]和r[j]：
如果s[i] == r[j], dp[i][j] = dp[i-1][j-1]   //无需替换操作
如果s[i] != r[j]，那么通过对s在i位置操作
1) 插入r[j]，dp[i][j] = dp[i][j-1] + 1
2) 删除s[i]，dp[i][j] = dp[i-1][j] + 1
3) 替换s[i]，dp[i][j] = dp[i-1][j-1] + 1
因此，dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1)
我们只要维护一个dp[][]数组，那么dp[lenS][lenR]就是s和r之间最小的编辑距离
初始状态下，dp[0][j] = j，dp[i][0] = i
 */
class EditDistance {

    public int calDistance(String a, String b){
        if (a == null && b == null) {
            return 0;
        }
        
        int aLen = a.length(), bLen = b.length();
        int[][] distance = new int[aLen+1][bLen+1]; // we need distance[aLen][bLen] as the result
        for (int i = 0; i < aLen; i++) {
            distance[i][0] = i;
        }
        for (int i = 0; i < bLen; i++) {
            distance[0][i] = i;
        }
        
        for (int i = 1; i <= aLen; i++) {
            char cA = a.charAt(i-1);
            for (int j = 1; j <= bLen; j++) {
                char cB = b.charAt(j-1);
                if (cA == cB) {
                    distance[i][j] = distance[i-1][j-1];
                }else {
                    int delete = distance[i-1][j] + 1;
                    int insert = distance[i][j-1] + 1;
                    int replace = distance[i-1][j-1] + 1;
                    int smaller = Math.min(delete, insert);
                    distance[i][j] = Math.min(smaller, replace);
                }
            }
        }
        
        return distance[aLen][bLen];
    }
}
