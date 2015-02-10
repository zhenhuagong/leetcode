package com.simongong;

/*
You are given an n*n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

思路：
跟旋转打印数组类似，由外到里逐层剥离，维护剩余元素最外层的四个边界
注意点：
1. 循环次数以rounds = data.length为起点，完成一层旋转后应该减2
2. 如果剩下rounds = 1，说明是奇数行，剩余一个中心元素不用旋转
 */
public class RotateImage {

    public static void main(String[] args) {
        Integer[][] testData = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        Utils.printMatrix("Original", testData);
        rotate(testData);
        Utils.printMatrix("Rotated!", testData);
    }

    public static void rotate(Integer[][] data){
        if(data == null || data.length == 0 || data[0].length == 0){
            return;
        }
        
        int left = 0, right = data[0].length - 1, top = 0, bottom = data.length - 1;
        int rounds = data.length;
        while(rounds > 1){  // if n is odd, leave the center element untouched
            // Rotate cycle
            for(int i = 0; i < rounds - 1; i++){
                int tmp = data[top][left+i];
                data[top][left+i] = data[bottom-i][left];   // left to top
                data[bottom-i][left] = data[bottom][right-i];   // bottom to left
                data[bottom][right-i] = data[top+i][right]; // right to bottom
                data[top+i][right] = tmp;   // top to right
            }
            top++;
            right--;
            bottom--;
            left++;
            rounds -= 2;    //Important! `rounds` indicates the length of the dimension of the left elements
        }
    }
}
