package com.simongong;

import java.util.ArrayList;
import java.util.List;

/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

思路：
1. 递归
一次递归扫描当前最外围的一圈，用(x, y)记录每圈的左上角元素，扫描完一圈之后(x++, y++)。
同时用rows, cols记录还剩多少没扫。
注意： 
扫描的时候，对于行和列的边界，有两种处理方式。
i. 扫行的时候要扫到边界，扫列的时候不扫边界。 
比如测试例子中的，程序扫第一圈的过程是123 6 987 4 5。
ii. 行与列都统一对待，不扫最后一个元素，即边界判断统一采用[0, 0) （循环的时候更简洁易读）
比如测试例子中的，程序扫第一圈的过程是12 36 98 74 5
这里要使用第一种，因为后者有漏洞，当最后一趟只剩一个元素的时候，由于边界判断是[0, 0)，将不会扫描剩余的那一个元素。

2. 循环，非递归
思路类似，都是逐圈扫描。用变量保存状态。
关键点是，如何设定需要保存的状态。
比较好的方案是着眼于数组的下标，保存四条边界，这样在取元素的时候能够更简洁，减小出错概率。

3. 循环。 
与保存四条边界的思路类似，着眼于扫描一圈的每一步操作，然后保存左上角起点和已经剥去的外层。
为了方便递增和递减，着眼于扫描一圈的每一步操作，共四步操作，设定一个四维向量，并实例化为2个单位方向向量。
这样也可以减轻下标计算的繁琐度，但保存的变量较多，不如第二种思路简洁。

 */

public class SpiralMatrix {
    public static final double NS_MS_GAP = 1000000d;

    public static void main(String[] args){
        int[][] matrix3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix4 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        
        long startTime = System.nanoTime();
        Utils.printList("spiralOrderRecursive: ", spiralOrderRecursive(matrix3), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
        
        startTime = System.nanoTime();
        Utils.printList("spiralOrderIteration: ", spiralOrderIteration(matrix3), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
        
        startTime = System.nanoTime();
        Utils.printList("spiralOrderVector: ", spiralOrderVector(matrix3), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
        
        startTime = System.nanoTime();
        Utils.printList("spiralOrderRecursive: ", spiralOrderRecursive(matrix4), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
        
        startTime = System.nanoTime();
        Utils.printList("spiralOrderIteration: ", spiralOrderIteration(matrix4), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
        
        startTime = System.nanoTime();
        Utils.printList("spiralOrderVector: ", spiralOrderVector(matrix4), " ");
        System.out.println("Time consumed: \t" + ((System.nanoTime() - startTime) / NS_MS_GAP) + "ms\n");
    }
    
    // Recursively scan, like peeling an union
    public static List<Integer> spiralOrderRecursive(int[][] matrix){
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
        
        peelUnion(matrix, 0, 0, matrix.length, matrix[0].length, result);
        
        return result;
    }
    
    // Use iteration instead of recursion
    // Accordingly, we need to maintain more variables to record the current status
    public static List<Integer> spiralOrderIteration(int[][] matrix){
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // left,right,top,bottom represent the current boundary index of left elements 
        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;
        
        while(left <= right && top <= bottom){
            // top row
            for(int i = left; i <= right; i++){ // i represent the index of element
                result.add(matrix[top][i]);
            }
            
            // right column
            for(int i = top + 1; i < bottom; i++){
                result.add(matrix[i][right]);
            }
            
            // down row
            if(top != bottom){
                for(int i = right; i >= left; i--){
                    result.add(matrix[bottom][i]);
                }
            }
            
            // left column
            if(left != right){
                for(int i = bottom - 1; i > top; i--){
                    result.add(matrix[i][left]);
                }
            }
            
            left++;
            top++;
            right--;
            bottom--;
        }
        
        return result;
    }
    
    // Use a vector to indicate the direction of a step in scanning cycle
    // Very useful for matrix traversal, can be easily applied to cases with 3 or more dimension
    public static List<Integer> spiralOrderVector(int[][] matrix){
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int visitedRows = 0;
        int visitedCols = 0;
        
        // Indicate the direction of row scanning action
        // in the order of steps of a scan cycle: lefttop -> righttop -> rightbottom -> leftbottom
        // for x[], 1: left-to-right, -1: right-to-left, 0: no scan action
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        
        // Indicate the step action in the whole scan action of one lap
        // 0: right, 1: down, 2: left, 3: up
        int action = 0;
        
        int startX = 0;
        int startY = 0;
        
        int candidateNum = 0;
        int step = 0;
        while(true){
            if(x[action] == 0){
                // scan column
                candidateNum = rows - visitedRows;
            }else{
                candidateNum = cols - visitedCols;
            }
            
            if(candidateNum <= 0){  // No row/col left, finish scanning
                break;
            }
            
            result.add(matrix[startX][startY]);
            step++;
            
            if(step == candidateNum){   // finish a row/column
                step = 0;
                visitedRows += (x[action] == 0 ? 0 : 1);
                visitedCols += (y[action] == 0 ? 0 : 1);
                
                action++;   // Go to next step in the cycle of scanning a lap
                action = action % 4;
            }
            
            startX += y[action];
            startY += x[action];
        }
        
        return result;
    }

    // (x, y) represents the current left-top element in the left elements
    // rows, cols represent the left rows and cols to scan
    private static void peelUnion(int[][] matrix, int x, int y, int rows, int cols, List<Integer> result) {
        if(rows <= 0 || cols <= 0){ // finish peeling
            return;
        }
        
        // top row
        for(int i = 0; i < cols; i++){  // for better simplicity, only count actual loop times in loop condition
            result.add(matrix[x][y+i]); // find the actual index of element
        }
        
        // right column
        for(int i = 1; i < rows - 1; i++){  // Note that loop in [1, rows-1] for right column
            result.add(matrix[x+i][y+cols-1]);
        }
        
        // down row
        if( rows > 1){
            for(int i = cols-1; i >= 0; i--){
                result.add(matrix[x+rows-1][y+i]);
            }
        }
        
        // left column
        if(cols > 1){
            for(int i = rows - 2; i > 0; i--){  // Note that loop in [1, rows-2] for left column
                result.add(matrix[x+i][y]);
            }
        }
        
        peelUnion(matrix, x+1, y+1, rows-2, cols-2, result);
    }
}
