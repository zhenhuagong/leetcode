package com.simongong;

import java.util.ArrayList;

/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

求C(k, n)。
思路:
组合是无序的，因此要丢弃已经扫过的数。
使用DFS，递归函数为combine(int n, int k, int start, int[] selected, List<List<Integer>> results)。 当k == 0时，返回selected。

 */
public class Combinations {

    public static void main(String[] args) {
        Utils.printList("Combinations of C(2, 4) as", combinationRecursive(4, 2), "\n");
    }

    public static ArrayList<ArrayList<Integer>> combinationRecursive(int n, int k){
        if(n <= 0 || k <= 0 || n < k){
            return null;
        }
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> selected = new ArrayList<Integer>();
        dfs(n, k, 1, selected, results);    // start from 1
        return results;
    }
    
    private static void dfs(int n, int k, int start, ArrayList<Integer> selected, ArrayList<ArrayList<Integer>> results){
        if(k == 0){ // got a solution
            results.add(new ArrayList<Integer>(selected));  // copy selected into results
            return;
        }
        // Note the range of i
        for(int i = start; i <= (n - k + 1); i++){  // dfs all the solutions which start with i
            selected.add(i);
            dfs(n, k-1, i+1, selected, results);
            selected.remove(selected.size() - 1);   // remove tail after dfs returns
        }
    }
}
