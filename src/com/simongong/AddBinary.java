package com.simongong;

/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

思路：
没什么算法，就是从右到左逐位相加，注意进位carry的处理就行。
注意：
1. 可以根据a和b的长度来区分二者，保持a.length() > b.length()，循环b.length()次，然后把a剩余部分接过去。比较繁琐。
2. 另一种简洁的做法是只要a和b还剩余元素，就保持一样的处理。
这里采用第二种方式。
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "1110";
        String b1 = "1";
        String b2 = "11";
        
        System.out.println(a + " + " + b1 + " = " + add(a, b1));
        System.out.println(a + " + " + b2 + " = " + add(a, b2));
    }

    public static String add(String a, String b){
        if(a == null || a.length() == 0){
            return b;
        }
        if(b == null || b.length() == 0){
            return a;
        }
        
        StringBuilder sb = new StringBuilder();
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        int carry = 0;
        while(p1 >= 0 || p2 >= 0){  // a way of simplicity, avoid handling cases that length of a and b are different
            int sum = carry;
            if(p1 >= 0){
                sum += (a.charAt(p1) - '0');
            }
            if(p2 >= 0){
                sum += (b.charAt(p2) - '0');
            }
            char c = sum % 2 == 1 ? '1' : '0';
            sb.insert(0, c);    // use insert
            carry = sum / 2;
            p1--;
            p2--;
        }
        if(carry == 1){
            sb.insert(0, '1');
        }
        return sb.toString();
    }
}
