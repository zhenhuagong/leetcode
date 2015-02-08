package com.simongong;

import java.util.List;

public class Utils {

    public static <T> void printArray(String prefix, T[] data, String seperator){
        System.out.print(prefix + "\t");
        for(int i = 0; i < data.length - 1; i++){
            System.out.print(data[i].toString() + seperator);
        }
        System.out.print(data[data.length - 1].toString());
        System.out.println("");
    }
    
    public static <T> void printList(String prefix, List<T> data, String seperator){
        System.out.print(prefix + "\t");
        for(T elem : data){
            System.out.print(elem.toString() + seperator);
        }
        System.out.println("");
    }
    
    public static <T> void printMatrix(String prefix, T[][] matrix){
        System.out.println(prefix);
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(matrix[i][j].toString() + "\t");
            }
            System.out.println("");
        }
    }
    
    public static void swap(Integer[] data, int m, int n){
        int tmp = data[m];
        data[m] = data[n];
        data[n] = tmp;
    }
}
