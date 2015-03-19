package com.simongong.tree;

import java.util.ArrayList;
import java.util.Stack;

import com.simongong.extendedtype.TreeNode;

/*
Binary Tree Inorder Traversal

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

注意：
进栈顺序。先不断的推左子节点，然后弹出栈顶元素看起是否已被访问。是就添加到result，否则推进栈，并推进其右子节点。
因此需要两个额外的栈空间。

 */
public class BinaryTreePostorderTraversal {
    public ArrayList<Integer> postOrder(TreeNode root){
        if (root == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Boolean> visited = new Stack<>();
        TreeNode cur = root;
        while(true){
            while(cur != null){
                stack.push(cur);
                visited.push(false);
                cur = cur.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            cur = stack.pop();
            if (!visited.pop()) {
                stack.push(cur);
                visited.push(true);
            }else{
                result.add(cur.val);
            }
            cur = cur.right;
        }
        return result;
    }
    
    public ArrayList<Integer> postOrderRecursive(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
    
    private void dfs(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return;
        }
        dfs(root.left, result);
        dfs(root.right, result);
        result.add(root.val);
    }
}
