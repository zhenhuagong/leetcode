package com.simongong;

/*
Implement pow(x, n).

思路：
pow(x,n)就是求x的n次方，直观复杂度是O(n)，我们得加速这个过程。分析一下x^n这个式子，找二分的方法。
x的N次方可以看做：x^n = x^(n/2) * x^(n/2) * x^(n%2)
所以利用递归求解，当n==1的时候，x^n=x。

当然n是可以小于0的，2^(-3) = 1/(2^3)，也可以按照上面那个规律解决。
 */
public class Pow {

    public double pow(double x, int n){
        if(x == 0){
            return 0;
        }
        
        if(n < 0){
            return 1 / power(x, -n);
        }else{
            return power(x, n);
        }
    }
    
    private double power(double x, int n){
        if(n == 0){
            return 1;
        }
        
        double v = power(x, n/2);
        if(n % 2 == 0){
            return v * v;
        }else{
            return v * v * x;
        }
    }
}
