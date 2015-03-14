package com.simongong;
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

思路：
又是求最优解，而且包含了大量重复计算，尝试用动态规划：寻找dp函数，计算解向量D。
从底部开始分析，定义D[i,n]=区间[i,n]之间最小的cut数，n为字符串长度。
 a   b   a   b   b   b   a   b   b   a   b   a
                 i               j  j+1      n
目测，D[i,n] = Math.min(D[i,j] + D[j+1,n])
这是一个二维的函数，实际写代码时要维护一个二维向量，比较麻烦。所以要转换成一维dp，维护一个一维向量。
如果每次在i位置，往右扫描，每找到一个回文就算一次dp的话，对于函数
D[i]=区间[i,n]之间最小的cut数，有
D[i] = Math.min(1+D[j+1]) i<=j<n
其中，D[0] = -1。因为指当整个字符串判断出是回文时，其实应该是结果为0（没有任何切割），即D[0] + 1=0， 所以，应把D[0] 设置为-1
有了转移函数之后，剩下的问题就是如何判断[i,j]是回文？ 这也是一个dp问题。
定义函数
P[i][j]=true if [i,j]为回文，那么
P[i][j] = str[i] == str[j] && (P[i+1]P[j-1] || {i,j相邻或者是同一字符})

再次总结动态规划，它是解决下面这些性质类问题的一种方法：
1. 一个问题可以通过更小子问题的解决方法来解决，或者说问题的最优解包含了其子问题的最优解
2. 有些子问题的解可能需要计算多次（尝试更新多次，这个更新条件就是找到一个划分子问题的条件，比如本题中找到一个0<=j<i，使得p[j,i-1]==true）
3. 子问题的解存储在一张表格里，这样每个子问题只用计算一次
4. 需要额外的空间以节省时间
 */
public class PalindromePartitioningII {

    public int minCut(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] D = new int[len+1];
        D[0] = -1;
        
        boolean[][] P = new boolean[len][len];
        for (int i = 1; i <= len; i++) {
            D[i] = i-1; // initial D[i] with the worst case(cut [0, i] one by one)
            // loop check if [j,i-1] is palindrome
            // populate the value of column p[][i-1]
            // dp the solution of D[i]
            for (int j = 0; j < i; j++) {
                P[j][i-1] = false;
                if (s.charAt(j) == s.charAt(i - 1) && (i - 1 - j <= 2 || P[j+1][i-2])) {    //[j, i-1] is a palindrome
                    P[j][i-1] = true;
                    D[i] = Math.min(D[i], D[j] + 1);    //try to update D[i] when [j, i-1] is palindrome
                }
            }
        }
        return D[len];
    }
}
