package com.simongong;

/*
Write a function to find the longest common prefix string amongst an array of strings.

Input: ["abcd", "abcefdsaf", "abdre132", "abckdfjks"]
Output: "ab"

思路：
逐位判断，如果在某一位i上大家不一致，则返回任意字符串的substring(0, i-1)
在这个问题中，所有字符串的地位是平等的，因此拿第一个字符串作为外层循环对象
（也可以先计算所有字符串的长度，取最短的那个字符串作为外层循环对象）
内部循环其他字符串的相同位置，判断charAt(i)是否都与第一个字符串相等

 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] testData1 = {"abcd", "abcefdsaf", "abdre132", "abckdfjks"};
        System.out.println(longestCommonPrefix(testData1).equals("ab"));
    }

    public static String longestCommonPrefix(String[] data){
        String result = "";
        if(data == null || data.length == 0){
            return result;
        }
        result = data[0];
        int length = result.length();
        for(int i = 0; i < length; i++){
            char cursor = result.charAt(i);
            for(int j = 1; j < data.length; j++){
                if(data[j].length() < i || data[j].charAt(i) != cursor){    // NOTE. should take care of data[j].length() first
                    return result.substring(0, i);
                }
            }
        }
        return result;
    }
}
