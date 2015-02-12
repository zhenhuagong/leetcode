package com.simongong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an array of strings, return all groups of strings that are anagrams.
Note: All inputs will be in lower-case.
e.g.
Input: ["abc", "bca", "bac", "bbb", "bbca", "abcb"]
Output: ["abc", "bca", "bac", "bbca", "abcb"]

思路：
借助bitmap和霍夫曼树的思想，把字符串转化为二进制串，就能将字符串比较操作转换为^异或位运算，例如：
abc = 00...00111
bca = 00...00111
abc ^ bca = 0;

实现：
用一个32位的int来作为bitmap，刚好能覆盖26个字符集大小

另外，也可以不用bitmap，直接用string作为map的key，构造一个Map<Integer, List<String>>。 
先对string做处理： 转换成char[] chars，然后使用Arrays.sort(chars)，使得anagrams能一致化。
 */
public class Anagrams {

    public static void main(String[] args) {
        String[] testData = {"abc", "bca", "bac", "bbb", "bbca", "abcb"};
        Utils.printArray("Original data", testData, ",");
        
        Map<Integer, List<String>> anagrams = getAnagrams(testData);

        List<String> result = new ArrayList<String>();
        for(Map.Entry<Integer, List<String>> entry : anagrams.entrySet()){
            List<String> anagram = entry.getValue();
            if(anagram.size() > 1){    // Only count those who has more than 1 anagram
                result.addAll(anagram);
            }
        }
        
        Utils.printList("Find Anagrams", result, ",");
    }

    public static Map<Integer, List<String>> getAnagrams(String[] data){
        Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
        
        for(int i = 0; i < data.length; i++){
            int bitValue = stringToBit(data[i]);
            if(result.containsKey(bitValue)){
                result.get(bitValue).add(data[i]);
            }else{
                List<String> anagrams = new ArrayList<String>();
                anagrams.add(data[i]);
                result.put(bitValue, anagrams);
            }
        }
        return result;
    }
    
    /*
     * Convert a string to bitmap in int
     */
    public static int stringToBit(String s){
        int result = 0;
        int length = s.length();
        for(int i = 0; i < length; i++){
            // Set bit value in bitmap for each char of s
            result = setBit(result, s.charAt(i) - 'a');
        }
        return result;
    }
    
    /*
     * Set the bit at position in subject
     */
    public static int setBit(int subject, int position){
        if(position < 0 || (1 & (subject >> (position))) == 1){    // position is already set in subject, ignore
            return subject;
        }else{
            // set position bit to 1
            return subject ^ (1 << (position));
        }
    }
}
