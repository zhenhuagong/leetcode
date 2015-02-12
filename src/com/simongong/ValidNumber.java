package com.simongong;

/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

思路：
1. 构造一种数字模式的匹配方式，有效数字分为三种：
i. 数字
ii. 数字 + 一个小数点 + 数字
iii. 数字 + 一个e + (最多一个+/-号) + 数字
总结就是： (digit)(.e)+(digit)
其中digit本身包括了+/-号

2. 使用正则表达式
regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?"

 */
public class ValidNumber {

    public static void main(String[] args) {
        String testData1 = "0";
        String testData2 = " 0.1 ";
        String testData3 = "abc";
        String testData4 = "1 a";
        String testData5 = "2e10";
        String testData6 = "2e+10";
        
        System.out.println(isNumber(testData1) == true);
        System.out.println(isNumber(testData2) == true);
        System.out.println(isNumber(testData3) == false);
        System.out.println(isNumber(testData4) == false);
        System.out.println(isNumber(testData5) == true);
        System.out.println(isNumber(testData6) == true);
        
        System.out.println(isNumberRegex(testData1) == true);
        System.out.println(isNumberRegex(testData2) == true);
        System.out.println(isNumberRegex(testData3) == false);
        System.out.println(isNumberRegex(testData4) == false);
        System.out.println(isNumberRegex(testData5) == true);
        System.out.println(isNumberRegex(testData6) == true);
    }

    public static boolean isNumber(String data){
        boolean number = false;
        boolean expression = false;
        boolean floatNumber = false;
        if(data == null || data.trim().length() == 0){
            return number;
        }
        
        String trimmedData = data.trim();
        int length = trimmedData.length();
        
        for(int i = 0; i < length; i++){
            char c = trimmedData.charAt(i);
            if(c == 'e'){
                if(!number || expression){
                    return false;
                }
                expression = true;
                number = false;
            }else if(c <= '9' && c >= '0'){
                number = true;
            }else if(c == '.'){
                if(expression || floatNumber){
                    return false;
                }
                floatNumber = true;
            }else if(c == '+' || c == '-'){
                if(i != 0 && trimmedData.charAt(i-1) != 'e'){
                    return false;
                }
            }else{
                return false;
            }
        }
        return number;
    }
    
    public static boolean isNumberRegex(String data){
        if(data == null || data.trim().length() == 0){
            return false;
        }
        
        String regex = "[-+]?(\\d+.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        if(data.trim().matches(regex)){
            return true;
        }else{
            return false;
        }
    }
}
