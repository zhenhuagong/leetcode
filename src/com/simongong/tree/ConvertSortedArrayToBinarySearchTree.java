package com.simongong.tree;

import com.simongong.extendedtype.TreeNode;

/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

按BST的定义，对数组进行二分，mid作为根节点。
递归。递归返回的边界条件为： left > right, return null
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode convert(int[] nodes){
        if(nodes == null || nodes.length == 0){
            return null;
        }
        
        return divide(nodes, 0, nodes.length - 1);
    }
    
    private TreeNode divide(int[] nodes, int left, int right){
        if(left > right){
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nodes[mid]);
        root.left = divide(nodes, 0, mid-1);
        root.right = divide(nodes, mid+1, right);
        return root;
    }
}
