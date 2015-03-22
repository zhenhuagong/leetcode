package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

思路：
对链表删除元素，还是一样，首先建立一个dummy，保存最终链表的头结点
而对于重复元素，使用pre,cur扫描链表。当cur与cur.next重复时，找到下一个非重复元素，然后pre.next = cur即可删除
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode removeDuplicates(ListNode head){
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = pre.next;
        while(cur != null && cur.next != null) {    // we should check cur.next too because it's used inside the while loop
            if (cur.next.val == cur.val) {  // duplicates found
                while(cur != null && cur.val == pre.next.val){  // check cur before use cur.val
                    cur = cur.next;
                }
                // delete duplicates
                pre.next = cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        
        return dummy.next;
    }
}
