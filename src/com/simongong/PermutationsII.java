package com.simongong;

import java.util.ArrayList;

/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

思路：
跟之前的排列类似，这里多的处理，是去重问题。
我们先看原始的思路
1. 从path集合来判断，if(path.contains(data[i]))
思路一中有if(path.contains(data[i]))的判断，这就是一个计算排列中的去重。但在这道题里不适用了。得使用其他的去重判断方式。
看上去挺麻烦，我们试着先把原始数据排序，这样重复数据都是连续的，能简化问题。
接下来在DFS的时候，可以先判断前面的一个数是否和自己相等。若相等，再判断前面的数是否已被使用过，是则自己才能使用，这样就不会产生重复的排列了。
例子：1 2 3 4 4 4 5 6 7 8
444这个的选法只能:4, 44, 444连续这三种选法。第一次是用444，第二次用44/4，第三次是4/44

2. 手动操作。着眼于第一位置的元素，第一个位置有n中可能(每次把原始序列中第i个元素和第一个元素交换)，然后求子数组[2…n]的全排列。每次求出一个solution之后，重新把第i个元素和第一个元素交换过来。
当我们枚举第i个位置的元素时，若要把后面第j个元素和i交换，则先要保证[i…j-1]范围内没有和位置j相同的元素。有以下两种做法（1）可以每次在需要交换时进行顺序查找data[i…j-1]；（2）用哈希表来查重。
这种处理是基于DFS思路的，比较简单。

 */
public class PermutationsII {

    public static void main(String[] args) {
        Integer[] testData = {1, 1, 2, 3};
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
        boolean[] visited = new boolean[data.length];
        dfs(data, visited, path, results);
        return results;
    }

    private static void dfs(Integer[] data, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> results){
        if(path.size() == data.length){ // got a solution
            results.add(new ArrayList<Integer>(path));
            return;
        }
        
        // Note the rage of i
        for(int i = 0; i < data.length; i++){   // dfs all solutions that start with i
            if(visited[i] || (i != 0 && data[i] == data[i - 1] && visited[i - 1])){
                continue;
            }
            visited[i] = true;
            path.add(data[i]);
            dfs(data, visited, path, results);
            path.remove(path.size() - 1);
            visited[i] = false;
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
            if(i == index || !checkExists(data, data[i], index, i)){    // check if data[i] exists in [index, i-1]
                swap(index, i, data);
                path.add(data[index]);
                dfs(data, index+1, path, results);
                path.remove(path.size() - 1);
                swap(index, i, data);
            }
        }
    }
    
    private static void swap(int a, int b, Integer[] data){
        int t = data[a];
        data[a] = data[b];
        data[b] = t;
    }
    
    private static boolean checkExists(Integer[] data, int target, int start, int end){
        for(int i = start; i < end; i++){
            if(data[i] == target){
                return true;
            }
        }
        return false;
    }
}
