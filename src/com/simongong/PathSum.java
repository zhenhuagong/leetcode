package com.simongong;

/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

思路：
由于这里指定要找root-to-leaf的路径，因此降低了DFS的复杂度。我们只需要在各个叶子节点处做检查就行。
 */

public class PathSum {

    public boolean hasPath(TreeNode root, int sum){
        if (root == null) {
            return false
        }
        // check at leaf node
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }

        return hasPath(root.left, sum - root.val) || hasPath(root.left, sum - root.val);
    }
}