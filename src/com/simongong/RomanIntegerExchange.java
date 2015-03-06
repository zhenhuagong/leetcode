package com.simongong;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer, convert it to a roman numeral.
And vice versa.

Input is guaranteed to be within the range from 1 to 3999.

Roman Numerals Chart:
http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm

注意：
根据不同的需求，使用不同的数据结构。
 */
public class RomanIntegerExchange {

    public static void main(String[] args) {
        String dataRoman1 = "DCCVII";
        int dataInt1 = 707;
        System.out.println(romanToInt(dataRoman1) == dataInt1);
        System.out.println(intToRoman(dataInt1).equals(dataRoman1));
        
        String dataRoman2 = "DCCCXC";
        int dataInt2 = 890;
        System.out.println(romanToInt(dataRoman2) == dataInt2);
        System.out.println(intToRoman(dataInt2).equals(dataRoman2));
    }

    /*
     * 1. 直观地看，需要处理单个或两个罗马字符，比较繁琐
     * 2. 其实两个罗马字符的情况，都是有规律的，比如IV=4，其实就是V-I=4，也就是 num - I + V
     * 3. 因此对于IV，我们只要记录I值，看它与下一个字母V的大小情况，就能知道应该是加它还是减它了
     * 4. 这样，我们就逐字符扫描，基本操作是num + map.get(data.charAt(i))，碰到IV的情况，就num - map.get(data.charAt(i))
     */
    public static int romanToInt(String data){
        Map<Character, Integer> romanTable = new HashMap<Character, Integer>();
        romanTable.put('I', 1);
        romanTable.put('V', 5);
        romanTable.put('X', 10);
        romanTable.put('L', 50);
        romanTable.put('C', 100);
        romanTable.put('D', 500);
        romanTable.put('M', 1000);
        
        int len = data.length(), result = 0;
        for(int i = 0; i < len; i++){
            int cur = romanTable.get(data.charAt(i));
            if(i < len - 1 && cur < romanTable.get(data.charAt(i + 1))){
                result -= cur;
            }else{
                result += cur;
            }
        }
        return result;
    }
    
    /*
     * 1. 取出所有关键罗马数字，与对应的数字，建两个数组
     * 2. 根据罗马数字规则，从左到右来看，尽量先匹配大的
     * 3. 因此两个基础数组的构建也做成从左到右、由大到小的排列
    */
    public static String intToRoman(int data){
        int[] keyNumbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < keyNumbers.length){
            if(data >= keyNumbers[i]){
                sb.append(romanChars[i]);
                data -= keyNumbers[i];
            }else{
                i++;
            }
        }
        return sb.toString();
    }
}
