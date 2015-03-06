package com.simongong;

/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.
Note: The sequence of integers will be represented as a string.

思路：
1. 注意题目是输入n，要求输出一个由整数组成的"sequence"，这个序列的内容是跟n-1对应的序列内容相关的。比如
0 - 1
1 - 11 (two 1)
2 - 21 (one 2, one 1)
3 - 1211 (one 1, one 2, two 1)
4 - 111221
 */
public class CountSay {

    public static void main(String[] args) {
//        String testData1 = "21114450669";
//        System.out.println(countSay(testData1).equals("12312415102619"));
        
        String countSay9 = countSay(9);
        String countSay10 = countSay(10);
        System.out.println(countSay9);
        System.out.println(countSay10);
        System.out.println(countSay(countSay9).equals(countSay10));
        
        String countSay11 = countSay(11);
        String countSay12 = countSay(12);
        System.out.println(countSay11);
        System.out.println(countSay12);
        System.out.println(countSay(countSay11).equals(countSay12));
    }
    
    public static String countSay(int n){
        if(n <= 0){
            return null;
        }
        String result = "1";
        int num = 1;
        for(int j = 0; j < n-1; j++){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < result.length(); i++){
                if(i < result.length() - 1 && result.charAt(i) == result.charAt(i + 1)){
                    num++;
                }else{
                    sb.append(num);
                    sb.append(result.charAt(i));
                    num = 1;
                }
            }
            result = sb.toString();
        }
        return result;
    }
    
    public static String countSayRecursive(int n){
        if(n == 0){
            return null;
        }
        if(n == 1){
            return "1";
        }
        String s = countSayRecursive(n-1);
        StringBuilder sb = new StringBuilder();
        
        int len = s.length(), count = 0;
        for(int i = 0; i < len; i++){
            count++;
            if(i == len - 1 || (i < len - 1 && s.charAt(i) != s.charAt(i + 1))){
                sb.append(count);
                sb.append(s.charAt(i));
                count = 0;
            }
        }
        return sb.toString();
    }

    public static String countSay(String data){
        StringBuilder sb = new StringBuilder();
        int len = data.length(), count = 0;
        char cur = 'a';
        for(int i = 0; i < len; i++){
            if(cur == data.charAt(i)){
                count++;
            }else{
                if(count != 0){
                    sb.append(count);
                    sb.append(cur);
                    cur = data.charAt(i);
                    count = 1;
                }else{
                    cur = data.charAt(i);
                    count++;
                }
            }
        }
        sb.append(count);
        sb.append(cur);
        return sb.toString();
    }
}
