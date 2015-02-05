package com.simongong;

import java.util.Set;
import java.util.TreeSet;

/*
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

Return true because "catsanddog" can be segmented as "cat and dog".

思路：
1. 可以划分子串各自处理；2.子串的处理都类似。 因此考虑用DP
状态表达式：
canBreak[i]表示长度为i的字符串能否被word bread
递推公式：
canBreak[i] = canBreak[j] && dict.contains(data.substring[j, i]) 当j在(0 ~ (i-1)]范围时。
即，划分substring[0,i]为两个串，当左边满足canBreak且右边在字典中时，canBreak[i] = true
原理：
首先对字符串中间划一道，分成两个子串。如果左右满足word break，同时右边属于字典里的单词，则表示D[i]也是可以划分的，推出本次循环
注意点：
划分的时候，左边是闭区间，右边是开区间
初始化：
canBreak[0] = true
循环中止条件：
canBreak[len]

 */
public class WordBreak {

    public static void main(String[] args){
        String testData1 = "leetcode";
        Set<String> dict = new TreeSet<String>();
        dict.add("leet");
        dict.add("code");
        System.out.println(canWordBreak(testData1, dict));
    }
    
    public static boolean canWordBreak(String data, Set<String> dict){
        if(data == null){
            return false;
        }
        
        int length = data.length();
        if(length == 0){
            return true;
        }
        
        boolean[] canBreak = new boolean[length + 1];
        canBreak[0] = true;
        
        for(int i = 1; i <= length; i++){
            // Split the string at the index of i
            // Check if substring[0,i] is breakable
            canBreak[i] = false;
            for(int j = 0; j < i; j++){
                // for substring[0, i], traverse it and split it to
                // check if the previous part is breakable by `canBreak[j]`
                if(canBreak[j] && dict.contains(data.substring(j, i))){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[length];
    }
}
