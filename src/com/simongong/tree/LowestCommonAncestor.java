package com.simongong.tree;

import com.simongong.extendedtype.TreeNode;

/*
给你一棵树的根节点root，和两个节点a,b，求a和b的最近公共祖先节点。

这个问题有很多中思路，根据条件不同，选择不同解法：
1. BST
如果树是一颗二叉查找树，那么左右子树相对有序，求解思路也利用BST特性：
1) 当前遍历的node如果比a/b都大，说明那两个点都在当前node的左边，所以这两个node的祖先肯定在当前node的左边，所以往左边找。反之，往右边找。
2) 如果这两个点一个大于当前node一个小于当前node，说明当前node就是LCA。
3) 如果这两个点一个是另一个的祖先，那么这个点就是LCA。

2. 一般的binary tree
这种就是普通的递归查找了，利用树的左右子树递归特性：
1. 判断当前根节点是否是a/b，是则返回
2. 先从左子树找到a和b，记为left；再从右子树找a/b，记为right
3. 若left和right都不为空，则当前root就是LCA
4. 若left不空right空，则left就是LCA；反之right为LCA
5. 以上都不满足，那么a和b的LCA就不存在

3. 带parent域的binary tree
这就可以转化为寻找两条链表的交叉节点了

 */
public class LowestCommonAncestor {

    public TreeNode findLCAInBST(TreeNode root, TreeNode a, TreeNode b){
        if(root == null){
            return null;
        }
        
        int left = a.val, right = b.val;
        // make sure a is at the left side of b
        // since we are going to search LCA in BST
        if(left > right){
            int tmp = left;
            left = right;
            right = tmp;
        }
        
        TreeNode parent = null; // for the case of b is parent of a, return the parent of b
        while(true){
            if(root.val < left){    // search root.right
                parent = root;
                root = root.right;
            }else if(root.val > right){ // search root.left
                parent = root;
                root = root.left;
            }else if(root.val == left || root.val == right){
                return parent;
            }else{
                return root;
            }
        }
    }
    
    public TreeNode findLCAInBTree(TreeNode root, TreeNode a, TreeNode b){
        if(root == null){
            return null;
        }
        if(root == a || root == b){
            return root;
        }
        
        TreeNode left = findLCAInBTree(root.left, a, b);
        TreeNode right = findLCAInBTree(root.right, a, b);
        
        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;
        }else if(right != null){
            return right;
        }else{
            return null;
        }
    }
}
