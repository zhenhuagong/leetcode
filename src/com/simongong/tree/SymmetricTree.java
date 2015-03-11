package com.simongong.tree;

import java.util.Stack;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

思路：跟判断两棵树是否相等类似，只是迭代对象有点变化。
1. 递归。DFS，从第二层开始DFS判断左右子树是否是symmetric
2. 迭代。
 */
public class SymmetricTree {

    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) {
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        
        while(!stack1.isEmpty() && !stack2.isEmpty()){
            TreeNode cur1 = stack1.pop();
            TreeNode cur2 = stack2.pop();
            if (cur1.val != cur2.val) {
                return false;
            }
            
            if (cur1.left != null && cur2.right != null) {
                stack1.push(cur1.left);
                stack2.push(cur2.right);
            }else if (!(cur1.left == null && cur2.right == null)) {
                return false;
            }
            if (cur1.right != null && cur2.left != null) {
                stack1.push(cur1.right);
                stack2.push(cur2.left);
            }else if(!(cur1.right == null && cur2.left == null)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isSymmetricRecursive(TreeNode root){
        if(root == null){
            return true;
        }

        return dfs(root.left, root.right);
    }
    
    private static boolean dfs(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) {
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        
        return dfs(root1.left, root2.right) && dfs(root1.right, root2.left);
    }
}
