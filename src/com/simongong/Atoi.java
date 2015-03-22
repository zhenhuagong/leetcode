package com.simongong;

/*
 * Implement atoi to convert a string to an integer.
Hint: Carefully consider all possible input cases.
If you want a challenge, please do not see below and ask yourself what are the possible input cases.
Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
You are responsible to gather all the input requirements up front.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary
until the first non-whitespace character is found.
Then, starting from this character, takes an optional initial plus or minus sign
followed by as many numerical digits as possible, and interprets them as a numerical value.
The string can contain additional characters after those that form the integral number,
which are ignored and have no effect on the behavior of this function.
If the first sequence of non-whitespace characters in str is not a valid integral number,
or if no such sequence exists because either str is empty or it contains only whitespace characters,
no conversion is performed.
If no valid conversion could be performed, a zero value is returned.
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

注意点：
1. 空白字符 trim()
2. 起始的+/-号 isPositive
3. 是否越界，使用long型作为返回值，判断与Integer.MAX_VALUE的大小
 */

public class Atoi {
    public static void main(String[] args){
        System.out.println(atoi(" +32432 uiod"));
        System.out.println(atoi(" -32432 uiod"));
        System.out.println(atoi(" 2147483647 uiod"));
        System.out.println(atoi(" 2147483648 uiod"));
        System.out.println(atoi(" -2147483648 uiod"));
        System.out.println(atoi(" -2147483649 uiod"));
    }
    
    public static int atoi(String data){
        long result = 0L;
        data = data.trim();
        if(data.length() == 0){
            return 0;
        }
        boolean isPositive = true;
        for(int i = 0; i < data.length(); i++){
            char cur = data.charAt(i);
            if(i == 0 && cur == '+'){
                continue;
            }else if(i == 0 && cur == '-'){
                isPositive = false;
                continue;
            }
            if(cur > '9' || cur < '0'){ // break if it's not a valid digit
                break;
            }
            int digit = isPositive ? cur - '0' : '0' - cur;
            result = result * 10 + digit;
            
            if(result > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(result < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)result;
    }
}
