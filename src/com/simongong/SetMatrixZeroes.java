package com.simongong;

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

思路：
解题点就在于清空标志位存在哪里的问题。可以创建O(m+n)的数组来存储，但此题是希望复用已有资源。这里可以选择第一行和第一列来存储标志位。
1.先确定第一行和第一列是否需要清零
2.扫描剩下的矩阵元素，如果遇到了0，就将对应的第一行和第一列上的元素赋值为0
3.根据第一行和第一列的信息，已经可以讲剩下的矩阵元素赋值为结果所需的值了
4.根据1中确定的状态，处理第一行和第一列。

 */
public class SetMatrixZeroes {

    public static void main(String[] args){
        Integer[][] matrix = {
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 15, 16}
        };
        Utils.printMatrix("Matrix: ", matrix);
        setMatrixZeroes(matrix);
        Utils.printMatrix("Set Zeroes: ", matrix);
    }
    
    public static void setMatrixZeroes(Integer[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        
        // Flags to indicate if 1st row/col should be set to zero
        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Prepare the flags for left elements 
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                if(matrix[i][j] != 0){
                    continue;
                }
                // matrix[i][j] == 0, set the flags accordingly
                matrix[i][0] = 0;
                matrix[0][j] = 0;

             // Set flags for 1st row/col
                if(i == 0){
                    isFirstRowZero = true;
                }
                if(j == 0){
                    isFirstColZero = true;
                }
            }
        }
        
        // Set zeroes for non-first-row/col elements
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set zeroes for 1st row/col
        if(isFirstRowZero){
            for(int i = 0; i < cols; i++){
                matrix[0][i] = 0;
            }
        }
        if(isFirstColZero){
            for(int i = 0; i < rows; i++){
                matrix[i][0] = 0;
            }
        }
        
        return;
    }
}
