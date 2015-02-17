package com.simongong;
/*
Given a 2D binary matrix filled with 0's and 1's,
find the largest rectangle containing all ones and return its area.

思路：
还是用四条边界的思路，top = 0, right = data[0].length, bottom = data.length, left = 0
逐圈遍历二维数组，修改四条边界的最值
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        int[][] testData1 = {
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        System.out.println(maxRectangle(testData1) == 16);
        
        int[][] testData2 = {
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0}
        };
        System.out.println(maxRectangle(testData2) == 25);
    }

    public static int maxRectangle(int[][] data){
        if(data == null || data.length == 0 || data[0].length == 0){
            return 0;
        }
        
        int top = 0, right = data[0].length - 1, bottom = data.length - 1, left = 0;
        int minLeft = data[0].length - 1, maxRight = 0, minTop = data.length - 1, maxBottom = 0;
        while(left < right && top < bottom){
            for(int i = 0; i < data[0].length; i++){  // scan top row
                if(data[top][i] == 1){
                    minTop = Math.min(top, minTop);
                    break;
                }
            }
            for(int i = 0; i < data.length; i++){  // scan right col
                if(data[i][right] == 1){
                    maxRight = Math.max(right, maxRight);
                    break;
                }
            }
            for(int i = 0; i < data[0].length; i++){  // scan bottom row
                if(data[bottom][i] == 1){
                    maxBottom = Math.max(bottom, maxBottom);
                    break;
                }
            }
            for(int i = 0; i < data.length; i++){  // scan left col
                if(data[i][left] == 1){
                    minLeft = Math.min(left, minLeft);
                    break;
                }
            }
            top++;
            right--;
            bottom--;
            left++;
        }

        return (maxRight - minLeft + 1) * (maxBottom - minTop + 1);
    }
}
