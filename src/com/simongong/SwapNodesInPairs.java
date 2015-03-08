package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

思路：
1. 递归。先翻转后面的链表，得到新的Next，翻转当前的2个节点。最后返回新的头部。
2. 迭代。把2个节点当一个节点看待，就是翻转链表，需要多保存两个个状态节点：当前翻转区域的前驱节点，后继节点。
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {

    }

    public static ListNode swapNodesInPairsRecursive(ListNode head){
        if (head == null) {
            return null;
        }
        
        return reversePair(head);
    }
    
    public static ListNode reversePair(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next.next;
        next = reversePair(next);  // reverse the left nodes in pair first
        
        // reverse current pair
        ListNode tmp = head.next;
        head.next = next;
        tmp.next = head;
        
        return tmp;
    }
    
    public static ListNode swapNodsInPair(ListNode head){
        if (head == null || head.next == null) {    // abort if there is less than 1 node in list
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode next = pre.next.next.next; // need previous and next pointer to reverse a node
            
            ListNode tmp = pre.next;
            pre.next = pre.next.next;
            pre.next.next = tmp;
            tmp.next = next;
            
            pre = tmp;
        }
        return dummy.next;
    }
}
