package com.simongong;

import java.util.ArrayList;

/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

思路：
排列是有序的，思路跟求组合类似，只是不删除已扫过的元素。
premutation(int n, int start, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> results)
 */
public class Permutations {

    public static void main(String[] args) {
        Integer[] testData = {1, 2, 3};
        Utils.printList("Permutation of " + Utils.arrayStringfy(testData, ","), permutate(testData), "\n");
    }
    
    public static ArrayList<ArrayList<Integer>> permutate(Integer[] data){
        if(data == null || data.length == 0){
            return null;
        }
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        dfs(data, path, results);
        return results;
    }

    private static void dfs(Integer[] data, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> results){
        if(path.size() == data.length){ // got a solution
            results.add(new ArrayList<Integer>(path));
            return;
        }
        
        // Note the rage of i
        for(int i = 0; i < data.length; i++){   // dfs all solutions that start with i
            // ignore those ones that already selected
            if(path.contains(data[i])){
                continue;
            }
            path.add(data[i]);
            dfs(data, path, results);
            path.remove(path.size() - 1);
        }
    }
}
