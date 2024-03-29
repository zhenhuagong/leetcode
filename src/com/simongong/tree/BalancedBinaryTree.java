package com.simongong.tree;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

思路：
1. 递归。
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root){
        if (root == null) {
            return true;
        }

        boolean cut = false;
        if (root.left == null || root.right == null) {
            cut = true;
        }
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(getDepth(root.left, cut) - getDepth(root.right, cut)) <= 1;
    }

    private int getDepth(TreeNode root, boolean cut){
        if (root == null) {
            return -1;
        }
        if (cut && (root.left != null || root.right != null)) {
            // early return
            return 2;
        }

        return 1 + Math.max(getDepth(root.left, false), getDepth(root.right, false));
    }

    public boolean isBalancedRaw(TreeNode root){
        if (getDepthRaw(root) == -1) {
            return false;
        }else{
            return true;
        }
    }
    // return value -1 means root is not balanced
    private int getDepthRaw(TreeNode root){
        if (root == null) {
            return 0;
        }

        int left = getDepthRaw(root.left);
        int right = getDepthRaw(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }else{
            if (Math.abs(left - right) > 1) {
                return -1;
            }else{
                return Math.max(left, right) + 1;
            }
        }
    }
}
