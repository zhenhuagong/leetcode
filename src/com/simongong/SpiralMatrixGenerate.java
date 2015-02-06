package com.simongong;

/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

思路：
跟SpiralMatrix类似，由外到内逐层填充。关键就在于数组下标的计算。
这里不重复另外两种方法了，直接使用四条边界的方法。
另外：
这里的边界判断统一采用[0, 0)，剩下的中心元素最后单独处理。

 */
public class SpiralMatrixGenerate {

    public static void main(String[] args){
        int length = Integer.valueOf(args[0]);
        Utils.printMatrix("Spiral Matrix with " + length + " length", generateSpiralMatrix(length));
    }
    
    public static Integer[][] generateSpiralMatrix(int n){
        Integer[][] result = new Integer[n][n];
        
        if(n <= 0){
            return result;
        }
        
        int top = 0, right = n - 1, bottom = n - 1, left = 0;
        int num = 1;
        while(top < bottom && left < right){    // Start peeling
            // first row
            for(int i = left; i < right; i++){
                result[top][i] = num++;
            }
            
            // right column
            for(int i = top; i < bottom; i++){
                result[i][right] = num++;
            }
            
            // bottom row
            for(int i = right; i > left; i--){
                result[bottom][i] = num++;
            }
            
            // left column
            for(int i = bottom; i > top; i--){
                result[i][left] = num++;
            }
            
            top++;
            right--;
            bottom--;
            left++;
        }
        // Center element
        if(n % 2 != 0){
            result[n/2][n/2] = num;
        }
        
        return result;
    }
}
