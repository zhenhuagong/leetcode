package com.simongong.tree;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
 

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
             
思路：
例子中给的树的结构像是小顶堆。
主要就是寻找next。利用递归性质，重新调整树的节点相对关系：
先序遍历，对于节点node，之后遍历到的值作为新的right连起来，left变为空。
因为会更新right域，所以需要提前保存right。

或者，根据left是否为空，先连接left tree, 然后再连接right tree。
使用一个tail 来记录链的结尾，方便在完成left tree的连接之后，去连接right tree。
 */
public class FlattenBinaryTreeToLinkedList {

    TreeNode lastVisited = null;
    public void flatten(TreeNode root){
        if(root == null){
            return;
        }
        
        TreeNode origRight = root.right;
        if(lastVisited != null){
            lastVisited.left = null;
            lastVisited.right = root;
        }
        lastVisited = root;
        flatten(root.left);
        flatten(origRight);
    }
    
    public TreeNode flatten2(TreeNode root){
        if(root == null){
            return null;
        }
        
        // initial the root
        root.left = null;
        root.right = null;
        
        TreeNode tail = root;   // use to connect next
        if(root.left != null){
            tail.right = root.left;
            tail = flatten2(root.left); // flatten all nodes in the left tree
        }
        if(root.right != null){
            tail.right = root.right;
            tail = flatten2(root.right);
        }
        
        return tail;
    }
}
