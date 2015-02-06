package com.simongong;

public class RoundupDouble {

    public static void main(String[] args) {
        double adjust = 0.5d;
        double test1 = 1.5d;
        double test2 = 1.5001d;
        double test3 = 1.4999d;
        System.out.println("current:" + Math.ceil(test1));
        System.out.println("minus adjust: " + Math.ceil(test1 - adjust));
        System.out.println("Math.round : " + Math.ceil(Math.round(test1)));

        System.out.println("current:" + Math.ceil(test2));
        System.out.println("minus adjust: " + Math.ceil(test2 - adjust));
        System.out.println("Math.round : " + Math.ceil(Math.round(test2)));
        
        System.out.println("current:" + Math.ceil(test3));
        System.out.println("minus adjust: " + Math.ceil(test3 - adjust));
        System.out.println("Math.round : " + Math.ceil(Math.round(test3)));
        
        double compare1 = 0.0d;
        double compare2 = 0.00000001d;
        double compare3 = -0.00000001d;
        System.out.println(compare1 >= 0.0d);
        System.out.println(compare2 >= 0.0d);
        System.out.println(compare3 <= 0.0d);
    }
}
