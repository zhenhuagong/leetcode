package com.simongong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a string, S, and a list of words, L, that are all of the same length.
Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

思路：
1. 使用HashMap来保存L中所有的字串。
2. 暴力破解之。使用i记录我们的查找结果字符串的位置，j记录单个单词的查找位置。j每次移动一个L中单词的位置。
3. 注意各种越界条件：i查到离结束还有L*N（L中所有单词总长）的时候，即需要停止。    j 也要考虑每一次查找的单词的长度。
4. 使用第二个HashMap来记录我们查到的单词。如果所有的单词都查到了，即可记录一个解。
 */
public class SubstringwithConcatenationofAllWords {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] l = {"foo", "bar"};
        Utils.printList("Indices", findIndices(s, l), ", ");
    }

    public static List<Integer> findIndices(String s, String[] l){
        List<Integer> indices = new ArrayList<Integer>();
        
        Map<String, Integer> targets = new HashMap<String, Integer>();
        Map<String, Integer> hits = new HashMap<String, Integer>();
        for(int i = 0; i < l.length; i++){
            if(!targets.containsKey(l[i])){
                targets.put(l[i], 1);
            }else{
                targets.put(l[i], targets.get(l[i]) + 1);
            }
        }
        int sLen = s.length(), l1Len = l[0].length();
        for(int i = 0; i <= sLen - l.length * l1Len; i++){
            hits.clear();
            int j;
            for(j = 0; j < l.length; j++){
                
            }
        }
        return indices;
    }
}
