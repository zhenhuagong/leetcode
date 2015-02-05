package com.simongong.tree;

/*
 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Note:
You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).

思路：
1. 由于要操作同层次的兄弟节点，因此首先考虑层次遍历
2. 但层次遍历需要O(lg(h))的空间复杂度，不符合题目要求
3. 把层次遍历修改一下，不用队列，而用两个指针，两层循环
4. 一个指针P1专门记录每一层的最左边节点，循环次数为树的深度；另一个指针P2扫描本层，把下一层的链接上，循环次数为树的宽度。下层链接完成后，将P1移动到它的左孩子即可。
这个算法的空间复杂度是O(1). 没有额外的空间。
 */
public class PopulateNextRightPointer {

    public static void main(String[] args){

    }
    
    public static void pointRightNode(TreeLinkNode root){
        if(root == null){
            return;
        }

        TreeLinkNode leftEnd = root;
        while(leftEnd != null && leftEnd.left != null){
            TreeLinkNode cur = leftEnd;
            while(cur != null){ // scan the current level, link the child level
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                cur = cur.next;
            }
            leftEnd = leftEnd.left;
        }
    }
}
