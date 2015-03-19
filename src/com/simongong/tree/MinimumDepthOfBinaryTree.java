package com.simongong.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

思路：
1. 递归
要输出最短depth，使用DFS递归计算左右子树的最小depth。
1) 以树中的节点任意节点为递归返回判断边界。如果只有left/right一边子节点的话，不能返回0，要返回子节点的depth。（depth是root到leaf node）
2) 只以叶节点为判断边界

2. 迭代
按层遍历，碰到叶节点就返回。
 */
public class MinimumDepthOfBinaryTree {

    public int minDepthRec(TreeNode root){  // run time: 373ms
        if(root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left == 0 || right == 0){
            return left > right ? left+1 : right+1;
        }
        return Math.min(left, right) + 1;
    }
    
    public int minDepthRec2(TreeNode root){ // run time: 264ms
        if(root == null){
            return 0;
        }
        
        return dfs(root);
    }
    public int dfs(TreeNode root){
        if(root == null){
            return Integer.MAX_VALUE;
        }
        
        if(root.left == null && root.right == null){
            return 1;
        }
        
        return Math.min(dfs(root.left), dfs(root.right)) + 1;
    }
    
    // count level size by hand
    public int minDepth(TreeNode root){ // 229ms
        if(root == null){
            return 0;
        }
        
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        int nodesInThisLevel = 1;
        int nodesInNextLevel = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            nodesInThisLevel--;
            if(node.left == null && node.right == null){    // first time reach a leaf node, return
                return depth;
            }
            if(node.left != null){
                queue.add(node.left);
                nodesInNextLevel++;
            }
            if(node.right != null){
                queue.add(node.right);
                nodesInNextLevel++;
            }
            if(nodesInThisLevel == 0){
                nodesInThisLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
                depth++;
            }
        }
        return 0;
    }
    
    // count level size by queue size
    public int minDepth2(TreeNode root){ // 284ms
        if(root == null){
            return 0;
        }
        
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int nodesInThisLevel = queue.size();
            depth++;
            for(int i = 0; i < nodesInThisLevel; i++){
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null){    // first time reach a leaf node, return
                    return depth;
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return 0;
    }
}
