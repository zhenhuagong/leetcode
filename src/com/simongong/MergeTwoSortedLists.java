package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {

    public ListNode mergeSortedLists(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(head1 != null && head2 != null){
            if (head1.val < head2.val) {
                cur.next = head1;
                cur = cur.next;
                head1 = head1.next;
            }else {
                cur.next = head2;
                cur = cur.next;
                head2 = head2.next;
            }
        }
        
        if (head1 == null) {
            cur.next = head2;
        }
        if (head2 == null) {
            cur.next = head1;
        }
        
        return dummy.next;
    }
}
