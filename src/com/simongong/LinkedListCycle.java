package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

思路：
老套路，两个指针一快一慢地走。如果二者相遇，则有环；如果快指针走到null，则无环。
 */
public class LinkedListCycle {

    public static void main(String[] args) {

    }

    public static boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }
        
        ListNode oneStep = head, twoStep = head;
        while(twoStep.next != null && twoStep.next.next != null){
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
            if(oneStep == twoStep){
                return true;
            }
        }
        return false;
    }
}
