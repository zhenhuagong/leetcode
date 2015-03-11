package com.simongong.tree;

import java.util.ArrayList;
import java.util.Stack;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

思路：
1. 递归。DFS
2. 迭代，Stack
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {

    }

    public static ArrayList<Integer> preOrder(TreeNode root){
        if (root == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return result;
    }
    
    public static ArrayList<Integer> preOrderRecursive(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    
    private static void dfs(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return;
        }
        result.add(root.val);
        dfs(root.left, result);
        dfs(root.right, result);
    }
}
