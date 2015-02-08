package com.simongong;

/*
There are N children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:
1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

思路：
使用一个数组记录每一个小孩应得的糖果的数目
1.初步安排。从左往右扫描，如果遇到上升区间，就给小孩比左边多一个糖，否则就给1个糖果。
2.收割。从右往左扫描，如果遇到上升区间，就给小孩比右边多一个糖。同时，应该与1步算出的值取一个大值（因为有可能要给小孩更多糖果才可以满足题设）。然后累加。
 */
public class Candy {

    public static void main(String[] args){
        
    }
    
    public static int candy(int[] data){
        int result = 0;
        if(data == null || data.length == 0){
            return result;
        }
        
        int[] candyCount = new int[data.length];
        
        // traverse from left to right
        for (int i = 0; i < data.length; i++) {
            if (i > 0 && data[i] > data[i-1]) {
                candyCount[i] = candyCount[i-1] + 1;
            }else{
                candyCount[i] = 1;
            }
        }
        // traverse from right to left and calculate sum
        // add more candies if data[i] fails to meet the requirement
        for (int i = data.length - 1; i >= 0; i--) {
            if (i < data.length - 1 && data[i] > data[i+1]) {
                candyCount[i] = Math.max(candyCount[i], candyCount[i+1] + 1);
            }
            result += candyCount[i];
        }
        return result;
    }
}
