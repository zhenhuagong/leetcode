package com.simongong.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.simongong.extendedtype.TreeNode;

/*
按层遍历二叉树： 队列 + 内循环
 */
public class BinaryTreeLevelOrderTraversal {

    public void traverseByOrder(TreeNode root){
        if(root == null){
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
    }
}
