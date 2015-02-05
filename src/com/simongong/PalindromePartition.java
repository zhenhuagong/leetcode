package com.simongong;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]

http://blog.csdn.net/u011095253/article/details/9177451
*/
public class PalindromePartition {

    public static void main(String[] args){
        String testData1 = "aab";

    }

    public static void printList(List<Object> data){

    }

    public static ArrayList<ArrayList<String>> partition1(String s){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> solution = new ArrayList<String>();
        directDfs(result, solution, s);
        return result;
    }

    public static void directDfs(ArrayList<ArrayList<String>> result, ArrayList<String> solution, String s){
        if (s.length() == 0) {  // Reach boundary, get a solution
            result.add(new ArrayList<String>(solution));
        }
        for (int i = 1; i <= s.length(); i++) {
            String substr = s.substring(0, i);
            if(isPalindrome(substr)){
                solution.add(substr);
                directDfs(result, solution, s.substring(i));
                solution.remove(solution.size() - 1);   // Remove an empty element at last position
            }
        }
    }

    public static boolean isPalindrome(String s){
        int i = 0, j = s.length() - 1;
        while(i < j){
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}