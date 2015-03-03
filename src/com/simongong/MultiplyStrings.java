package com.simongong;

/*
Given two numbers represented as strings, return multiplication of the numbers as a string.
Note: The numbers can be arbitrarily large and are non-negative.

思路：
1 模拟手动计算的过程。建立数组，双层循环遍历两个string，把单位的乘积累加到数组相应的位置
2 叠加结果数组中的每一位，处理进位
3 注意前导零的corner case
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String testData1 = "154";
        String testData2 = "8035";
        System.out.println(multiply(testData1, testData2).equals(String.valueOf(154 * 8035)));
    }

    public static String multiply(String operant1, String operant2){
        if(operant1 == null || operant2 == null){
            return null;
        }
        
        int len1 = operant1.length(), len2 = operant2.length();
        int[] sum = new int[len1 + len2];
        
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                // do the `reverse` logic here
                sum[i + j] += (operant1.charAt(len1 - 1 - i) - '0') * (operant2.charAt(len2 - 1 - j) - '0');
            }
        }
        
        // accumulate each digit
        int carry = 0;
        for(int i = 0; i < len1 + len2; i++){
            sum[i] += carry;
            carry = sum[i] / 10;
            sum[i] %= 10;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len1 + len2; i++){
            sb.insert(0, sum[i] + "");
        }
        
        // delete the leading 0
        while(sb.charAt(0) == '0' && sb.length() != 1){
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
}
