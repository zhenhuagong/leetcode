package com.simongong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
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
1. 由于candidates是递增的，因此使用两头夹来相加。从右侧扫的便利为最外层
2. 先找右边的数，如果candidate[i]能被target求余，则先求余remaining
3. 对于candidate[i]，需要尝试找target/candidate[i] 次解
3. 从左侧找减去target*m后的余数，也要判断candidate[j]%remaining求余。一直到candidate[j] > remaining || candidate[j] > target - candidate[i]为止。
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
