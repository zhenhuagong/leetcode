package com.simongong;

/*
Divide two integers without using multiplication, division and mod operator.

思路：
基本思想是不断地减掉除数，直到为0为止。但是这样会太慢。
由于目标是把被除数减到除数的(1,2)倍，可以用2分法来加速这个减的过程。不断对除数*2，用位运算实现，直到它比被除数还大为止。
加倍的同时，也记录下count，将被除数减掉加倍后的值，并且结果+count。
注意点：
1. 最小值的越界问题。对最小的正数INT_MIN取abs，得到的还是它。 因为最小的正数的绝对值大于最大的正数（INT）
Math.abs(Integer.MIN_VALUE) //-2147483648
Math.abs((long)Integer.MIN_VALUE)   //2147483648
Math.abs(Integer.MAX_VALUE) //2147483647
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        int a = 15, b = 4;  // normal case
        System.out.println(divide(a, b) == 3);
        
        a = 15;
        b = 0;  // divisor is zero
        try{
            System.out.println(divide(a, b) == Integer.MIN_VALUE);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        
        a = 0;
        b = 4;  // dividend is zero
        System.out.println(divide(a, b) == 0);
        
        a = 15;
        b = Integer.MIN_VALUE;  // divisor is INT_MIN
        System.out.println(divide(a, b) == 0);
        
        a = Integer.MIN_VALUE;
        b = -1; // result will be bigger than INT_MAX
        System.out.println(divide(a, b) == Integer.MAX_VALUE);
    }

    public static int divide(int dividend, int divisor) throws IllegalArgumentException{
        if(divisor == 0){
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        long result = 0;
        while(a >= b){
            for(long deduce = b, count = 1; deduce <= a; deduce <<= 1, count <<= 1){
                a -= deduce;
                result += count;
            }
        }
        
        // Check the highest digit to get the sign of result
        result = (((dividend ^ divisor) >> 31) & 1) == 1 ? -result: result;
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int)result;
    }
}
