package com.simongong;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

思路：
1. 如果直接从surrouned regions入手用BFS或DFS都比较麻烦，因为不知道结果是终止于X还是边界上的O, 只有终止于X时才应该翻转O。
2. 换个角度，用排除法，先考虑那些没有被包围的O, 找他们比较简单。O是否被X包围，是看O是否能“通”到区域的边界上去。
因此，基本思路就是从边界上的O出发，寻找相连通的其他O。连通的定义就是横向、纵向相连的。
这些与边界上的O相连通的O都是不会被转换的，剩下的O就是被X包围的。

实现上，先对board的四条边界上值为O的元素进行BFS扫描，搜索连通的O (使用DFS容易超时)，将其标记为'#'。
完成BFS处理之后，再次扫描全部board，遇到O就置为X，遇到#就置为O。


 */
public class SurroundedRegions {

    public static void main(String[] args) {

    }

    public static void solveIntuitive(char[][] board){
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int cols = board[0].length, rows = board.length;
        
        // BFS each element in the first and the last row
        for (int i = 0; i < cols; i++) {
            bfsSimple(board, 0, i);
            bfsSimple(board, rows - 1, i);
        }
        
        // BFS each element in the left and right column
        for (int i = 0; i < rows; i++) {
            bfsSimple(board, i, 0);
            bfsSimple(board, i, cols - 1);
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }
    
    private static void bfsSimple(char[][] board, int row, int col){
        int rows = board.length, cols = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(row * cols + col);  // serialize the element in sequence number
        
        while(!queue.isEmpty()){
            int index = queue.poll();
            
            if (index < 0 || index >= rows * cols) {    // index out of bounds
                continue;
            }
            
            int x = index / cols;
            int y = index % rows;
            if (board[x][y] != 'O') {
                continue;
            }
            
            board[x][y] = '#';
            queue.offer(index + 1); // move right
            queue.offer(index - 1); // move left
            queue.offer(index + cols);  // move down
            queue.offer(index - cols);  // move up
        }
    }
    
    public static void surroundedRegions(char[][] board){
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int width = board[0].length, height = board.length;
        int code = Math.max(width, height);
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == height - 1 || j == 0 || j == width - 1)) {
                    board[i][j] = '#';
                    bfs(board, height, width, i, j, code);
                }
            }
        }
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }
    
    // mocks 4 directions that an element can possibly move next
    private static int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    private static void bfs(char[][] board, int height, int width, int i, int j, int code){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i * code + j);  // transform two-dimension-index into on-dimension
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            int row = temp / code;
            int col = temp % code;
            for (int k = 0; k < directions.length; k++) {
                if (row + directions[k][0] < height && row + directions[k][0] >= 0 && col + directions[k][1] < width && col + directions[k][1] >= 0) {
                    if (board[row + directions[k][0]][col + directions[k][1]] == 'O') { // an accessible 0 is found
                        board[row + directions[k][0]][col + directions[k][1]] = '#';
                        queue.offer((row + directions[k][0]) * code + col + directions[k][1]);
                    }
                }
            }
        }
    }
}
