package com.simongong.tree;

import java.util.ArrayList;
import java.util.Stack;

import com.simongong.extendedtype.TreeNode;

/*
Binary Tree Inorder Traversal

Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

注意：
进栈顺序。先不断的推左子节点，然后访问，然后推进右子节点。

 */
public class BinaryTreeInorderTraversal {
    public ArrayList<Integer> inOrder(TreeNode root){
        if (root == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(true){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }
    
    public ArrayList<Integer> inOrderRecursive(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    
    private void dfs(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return;
        }
        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }
}
