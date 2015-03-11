package com.simongong;

import java.util.ArrayList;

/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

思路：
排列是有序的，思路跟求组合类似，只是不删除已扫过的元素。但要去除重复元素，有两种思路：
1. 从path集合来判断，if(path.contains(data[i]))
2. 手动操作。着眼于第一位置的元素，第一个位置有n中可能(每次把原始序列中第i个元素和第一个元素交换)，然后求子数组[2…n]的全排列。
每次求出一个solution之后，重新把第i个元素和第一个元素交换过来。

第一种思路有点偷懒，没有index的改变，看不出“深度”的变化，并非是典型的dfs。
第二种思路更清晰，是典型的dfs思路。
 */
public class Permutations {

    public static void main(String[] args) {
        Integer[] testData = {1, 2, 3};
        long timer1 = System.nanoTime();
        Utils.printList("Permutation of " + Utils.arrayStringfy(testData, ","), permutate(testData), "\n");
        long timer2 = System.nanoTime();
        Utils.printList("PermutationDFS of " + Utils.arrayStringfy(testData, ","), permutateDFS(testData), "\n");
        long timer3 = System.nanoTime();
        System.out.println("PermutationDFS is faster than Permutation by " + ((timer2 - timer1) / (timer3 - timer2)) + " times");
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
    
    public static ArrayList<ArrayList<Integer>> permutateDFS(Integer[] data){
        if(data == null || data.length == 0){
            return null;
        }
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        dfs(data, 0, path, results);
        return results;
    }
    
    private static void dfs(Integer[] data, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> results){
        if(path.size() == data.length){ // got a solution
            results.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = index; i < data.length; i++){
            swap(index, i, data);
            path.add(data[index]);
            dfs(data, index+1, path, results);
            path.remove(path.size() - 1);
            swap(index, i, data);
        }
    }
    
    private static void swap(int a, int b, Integer[] data){
        int t = data[a];
        data[a] = data[b];
        data[b] = t;
    }
}
