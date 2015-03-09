package com.simongong;

/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

思路：
1. 处理原始字符串：前后空格直接trim，中间空格用正则匹配替换.再反向扫描字符串，切分单词，逐个输出
2. 不用预处理原始字符串，直接反向扫描字符串，切分单词（需要StringBuilder来重组单词），逐个输出
 */
public class ReverseWordsInAString {

    public static void main(String[] args) {
        String testData = "the sky is blue";
        System.out.println(reverseWords(testData).equals("blue is sky the"));
    }

    public static String reverseWords(String words){
        if(words == null || words.length() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strs = words.split("\\s+");
        for(int i = strs.length - 1; i >= 0; i--){
            if(strs[i].equals("")){
                continue;
            }
            sb.append(strs[i]);
            if(i != 0){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
