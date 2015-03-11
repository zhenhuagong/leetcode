package com.simongong;

import java.util.ArrayList;

/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

思路：
组合的一个变形，最终序列中的每个位置的元素，有自己的一个候选集合，而不是共享同一个候选集合。

 */
public class LetterCombinationsOfAPhoneNumber {

    final public static String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        Utils.printList("Combinations for phone number of '23' as", combinePhoneLetters("23"), "\n");
        Utils.printList("Combinations for phone number of '356' as", combinePhoneLetters("356"), "\n");
    }

    public static ArrayList<ArrayList<Character>> combinePhoneLetters(String numbers){
        ArrayList<ArrayList<Character>> results = new ArrayList<ArrayList<Character>>();
        if(numbers == null || numbers.length() == 0){
            return results;
        }
        ArrayList<Character> selected = new ArrayList<Character>();
        dfs(numbers, 0, selected, results);
        return results;
    }
    
    public static void dfs(String numbers, int index, ArrayList<Character> selected, ArrayList<ArrayList<Character>> results){
        if(selected.size() == numbers.length()){
            results.add(new ArrayList<Character>(selected));
            return;
        }
        
        char[] candidates = map[numbers.charAt(index) - '0'].toCharArray();
        for(int i = 0; i < candidates.length; i++){
            selected.add(candidates[i]);
            dfs(numbers, index+1, selected, results);
            selected.remove(selected.size() - 1);
        }
    }
}
