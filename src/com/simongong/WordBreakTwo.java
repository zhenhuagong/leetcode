package com.simongong;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

思路：
跟v1的那题相比，差别在于，我们需要取到左边子串的值。因此，判断条件类似，只不过左右调换，变成： dict.contains(substring[0,i]) && canBreak[i]
然后是对字符串从右往左遍历，这样就是类似于bottom-up的方式，当访问到i元素的时候，canBreak[i] is ready
这种方式也叫DFS。DFS是bottom-up/right-to-left, DP是top-down/left-to-right
过程：
1. 用刀在字符串中切一刀。左边是i个字符，右边是len-i个字符。
i: 1 ~ len
如果： 左边是字典里的词，右边是可以wordbreak的，那么把左边的字符串加到右边算出来的List中，生成新的list返回。
初始化:
当输入字符串为空的时候，应该给出一个空解。这个很重要，否则这个递归是不能运行的。
递归的时候，i应该从1开始递归，因为我们要把这个问题分解为2个部分，如果你左边给0，那就是死循环。

 */
public class WordBreakTwo {

    public static void main(String[] args){
        String testData1 = "catsanddog";
        Set<String> dict = new TreeSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        System.out.println(wordBreak(testData1, dict));
    }
    
    public static List<String> wordBreak(String data, Set<String> dict){
        if(data == null || data.length() == 0 || dict == null){
            return null;
        }
        
        List<String> result = new ArrayList<String>();
        List<String> solution = new ArrayList<String>();
        int length = data.length();
        
        // Prepare the canBreak array
        boolean[] canBreak = new boolean[length + 1];
        canBreak[length] = true;
        for(int i = length - 1; i >= 0; i--){
            for(int j = i; j < length; j++){
                canBreak[i] = false;
                if(canBreak[j+1] && dict.contains(data.substring(i, j+1))){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        
        // Start dfs to get all the possible solutions
        dfs(data, dict, solution, result, 0, canBreak);
        
        return result;
    }
    
    public static void dfs(String s, Set<String> dict, List<String> solution, List<String> result, int index, boolean canBreak[]){
        int length = s.length();
        if(index == length){    // reach the end, find out a possible solution
            StringBuilder sb = new StringBuilder();
            for(String str : solution){
                sb.append(str);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
            return;
        }
        
        // If canBreak[index] == false, exit dfs
        if(!canBreak[index]){
            return;
        }
        
        for(int i = index; i < length; i++){
            String right = s.substring(index, i + 1);
            if(!dict.contains(right)){   // traverse until s.substring(index, i) is a word in dictionary
                continue;
            }
            // find out a valid word of s.substring(index, i)
            solution.add(right);
            dfs(s, dict, solution, result, i+1, canBreak);
            solution.remove(solution.size() - 1);
        }
    }
}
