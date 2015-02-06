package com.simongong;

import java.util.List;

public class Utils {

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
}
