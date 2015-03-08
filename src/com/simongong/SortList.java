package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Sort a linked list in O(n log n) time using constant space complexity.

思路：
分析几种常见的数组排序方法，根据链表的特性-写快读慢，因此可以用插入排序，归并排序。
又因为要求O(nlogn)的时间复杂度，因此用归并排序。

注意：
要以链表中间节点的前一个节点来划分，以避免当链表长度为2时陷入无限循环。1->2->null时，若返回2，则会无限循环下去。
 */
public class SortList {

    public static void main(String[] args) {

    }

    public static ListNode mergeSort(ListNode head){
        if (head == null || head.next == null) {    // break point
            return head;
        }
        
        ListNode midPre = getMidPre(head);  // get the node previous to the middle node
        ListNode right = midPre.next;
        midPre.next = null;
        
        ListNode left = mergeSort(head);
        right = mergeSort(right);
        
        return mergeLinkedList(left, right);
    }
    
    // get the node previous to the middle one in the list
    public static ListNode getMidPre(ListNode head){
        ListNode oneStep = head, twoStep = head.next;
        while(twoStep != null && twoStep.next != null){
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }
        return oneStep;
    }
    
    // merge a two sorted linked list
    public static ListNode mergeLinkedList(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            }else{
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head1 : head2;
        
        return dummy.next;
    }
}
