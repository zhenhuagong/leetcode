package com.simongong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

思路：
求和问题，为了简化，先把candidates排序，这样方便执行DFS（提高了剪枝的效率）。
类似于combination问题，也是用DFS，不同的是递归起点： 由于candidates是递增的了，下一个取值可以是i本身或是i的下一个。

 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        Utils.printList("Solution set is \n", combinationSum(candidates, target), "\n");
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0){
            return results;
        }
        
        Arrays.sort(candidates);    // Remove duplicate candidates to avoid duplicate solutions
        
        dfs(candidates, target, new ArrayList<Integer>(), results, 0);

        return results;
    }
    
    public static void dfs(int[] candidates, int target, List<Integer> path, List<List<Integer>> results, int index){
        if(target < 0){
            return;
        }
        if(target == 0){
            results.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = index; i < candidates.length; i++){
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], path, results, i);  // Point! use i as next index
            path.remove(path.size() - 1);   // back
        }
    }
}
