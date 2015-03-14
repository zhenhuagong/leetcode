package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Sort a linked list using insertion sort.

思路：
插入排序一句话：用cur遍历原序列，把cur插到已排好序的[1, cur-1]序列中对应的位置。
还是用dummy node，链接到排序序列的头结点
每次先查找该插入的位置的前驱，这需要一个指针。然后取下该节点，插入到已排序序列，同时需要保存该节点在未排序序列的后续节点。
一共需要两个指针。
 */
public class InsertionSortList {

    public ListNode insertSort(ListNode head){
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        while(head != null){
            ListNode pre = dummy;
            // find the target position for head
            // NOTE: use `<=` to keep the original relative position of nodes with the same value
            while (pre != null && pre.next.val <= head.val) {
                pre = pre.next;
            }
            // unlink head from the original list, 
            ListNode headNext = head.next;
            head.next = pre.next;
            
            pre.next = head;
            head = headNext;
        }
        
        return dummy.next;
    }
}
