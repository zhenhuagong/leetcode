package com.simongong.tree;

import java.util.Stack;

import com.simongong.extendedtype.TreeNode;

/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

思路：
1. 递归。树本身的定义是递归，因此判断树是否相等自然也用递归。
2. 迭代。DFS遍历树，使用额外的栈空间来保存path。
 */
public class IsSameTree {

    public static void main(String[] args) {

    }

    public static boolean isSameTreeRecursive(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        
        return root1.val == root2.val && isSameTreeRecursive(root1.left, root2.left) && isSameTreeRecursive(root1.right, root2.right);
    }
    
    public static boolean isSameTree(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        
        stack1.push(root1);
        stack2.push(root2);
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            TreeNode cur1 = stack1.pop();
            TreeNode cur2 = stack2.pop();
            
            if(cur1.val != cur2.val){   // check node value first
                return false;
            }
            
            if(cur1.left != null && cur2.left != null){ // check left child
                stack1.push(cur1.left);
                stack2.push(cur2.left);
            }else{
                return false;
            }
            if(cur1.right != null && cur2.right != null){   // check right child
                stack1.push(cur1.right);
                stack2.push(cur2.right);
            }else{
                return false;
            }
        }
        return true;
    }
}
