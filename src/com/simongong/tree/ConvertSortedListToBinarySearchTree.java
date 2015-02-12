package com.simongong.tree;

import com.simongong.extendedtype.ListNode;
import com.simongong.extendedtype.TreeNode;

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

思路：
分析条件，升序的单链表，生成BST树。
生成树，用递归。不断寻找当前链表的中间结点，作为子树的根节点，然后递归生成左右子树。
实现方法：
1. 自顶向下，先生成根节点，再递归生成左右子树
2. 自底向上，先生成左右子树，再生成根节点

这里用第一种方法实现。
 */
public class ConvertSortedListToBinarySearchTree {

    public static void main(String[] args) {

    }

    public static TreeNode sortedLinkedListToBST(ListNode head){
        if(head == null){
            return null;
        }
        if(head.next == null){
            return new TreeNode(head.val);
        }
        
        // will get the previous node of the middle node of the list
        // Because we need to generate the left child tree and right child tree afterwards
        ListNode midLeft = getMidLeftInList(head);
        ListNode mid = midLeft.next;
        TreeNode root = new TreeNode(mid.val);
        midLeft.next = null;    // Important! [head, midLeft] will become the left child of root
        root.left = sortedLinkedListToBST(head);
        root.right = sortedLinkedListToBST(mid.next);   // [mid.next, tail] will become the right child of root
        return root;
    }

    /*
     * Get the left node of middle node in a list
     */
    private static ListNode getMidLeftInList(ListNode head) {
        ListNode oneStep = head, twoStep = head;
        while(twoStep != null){
            twoStep = twoStep.next;
            if(twoStep == null){
                break;
            }
            twoStep = twoStep.next;
            if(twoStep == null){
                break;
            }
            oneStep = head;
            head = head.next;
        }
        return oneStep;
    }
}
