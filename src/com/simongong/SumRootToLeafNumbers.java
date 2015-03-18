package com.simongong;

/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

思路：
DFS，保留path，当扫到叶节点时，累加和。
基本思路是把左右子树的path sum和相加，就得到最终的和。
dfs(root, preSum)
递归返回条件为到达叶节点或者空节点。
*/
public class SumRootToLeafNumbers {
    public int sumUp(TreeNode root){
        if (root == null) {
            return 0;
        }
        dfs(root, 0);

    }
    private int dfs(TreeNode root, int preSum){
        if (root == null) {
            return 0;
        }

        int cur = preSum * 10 + root.val;
        if (root.left == null & root.right == null) {
            return cur;
        }

        return dfs(root.left, cur) + dfs(root.right, cur);
    }
}