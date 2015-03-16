package com.simongong;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6]

思路：
跟Combination Sum一样的思路，区别还是DFS的起点问题。举例：
对于[1, 1, 2, 5, 6, 7, 10]，第一层DFS为
1 + DFS(1, 2, 5, 6, 7, 10)
2 + DFS(5, 6, 7, 10)
5 + DFS(6, 7, 10)
6 + DFS(7, 10)
7 + DFS(10)
也就是对于candidates[n]，solution的第一个元素取值问题：下一个候选取值只能是i的下一个，而且不能与前一个值相同。
 */
public class CombinationSumII {

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        Utils.printList("Solution set is \n", combineSum(candidates, 8), "\n");
    }

    public static ArrayList<ArrayList<Integer>> combineSum(int[] candidates, int target){
        if(candidates == null || candidates.length == 0 || target <= 0){
            return null;
        }
        
        Arrays.sort(candidates);
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        dfs(candidates, target, 0, path, results);
        return results;
    }
    
    private static void dfs(int[] candidates, int target, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> results){
        if(target == 0){    // got a solution
            results.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){ // went to a wrong path by DFS, cut it off
            return;
        }
        
        // for candidates of {1,1,2,5,6,7,10}, after add 1 into path, we should ignore the second 1
        // and put 2 into path so the path is like [1, 2, ...]
        for(int i = index; i < candidates.length; i++){ // dfs all possible solutions that starts with candidates[i] from candidates[i,...,n]
            if(i != index && candidates[i] == candidates[i - 1]){
                continue;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, path, results);
            path.remove(path.size() - 1);
        }
    }
}
