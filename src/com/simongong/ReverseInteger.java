package com.simongong;

/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
 */
public class ReverseInteger {

    public int reverse(int a){
        long result = 0L;
        while (a != 0) {
            result = result *10 + a % 10;
            a = a / 10;
        }
        
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int)result;
    }
}
