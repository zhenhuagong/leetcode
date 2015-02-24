package com.simongong;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

思路：
用DP的思路。
1. D[i] 表示前i个字符能解的方法。
2. D[i] 有2种解法：
 1）. 最后一个字符单独解码。 如果可以解码，则解法中可以加上D[i - 1]
 2）. 最后一个字符与上一个字符一起解码。 如果可以解码，则解法中可以加上D[i - 2]
 以上2种分别判断一下1个，或是2个是不是合法的解码即可。
 */
public class DecodeWays {

    public static void main(String[] args) {
        String testData1 = "12";
        System.out.println(decode(testData1) == 2);
        String testData2 = "12512";
        System.out.println(decode(testData2) == 6);
    }

    public static int decode(String cipher){
        if(cipher == null || cipher.length() == 0){
            return 0;
        }
        
        int length = cipher.length();
        int[] resultSets = new int[length + 1]; // make an extra element at the end
        for(int i = 0; i <= length; i++){
            if(i == 0){
                resultSets[i] = 1;
            }else{
                resultSets[i] = 0;
                if(i >= 2 && isValid(cipher.substring(i-2, i))){
                    resultSets[i] += resultSets[i - 2];
                }
                if(cipher.charAt(i - 1) != '0'){
                    resultSets[i] += resultSets[i - 1];
                }
            }
        }
        
        return resultSets[length];
    }
    
    private static boolean isValid(String s){
        int num = Integer.parseInt(s);
        return num >= 10 && num <= 26;
    }
}
