package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

思路：
1. 两个指针，p1先走k步，p2再开始走。当p1到尾节点时，p2到了新的尾节点。 - 但该方式仅适用于k <= list.length()的情况，不是真正的旋转。
2. 为了处理k大于链表长度的场景，需要先求出链表长度len，然后实际rotate steps = k % len;
3. 还可以在求出链表长度之后，先将链表收尾连起来成为循环链表，这样不用重置p1，而且。然后继续往前跑len - k % len步，就找到break point了。直接将其next置null即可。这种方式只需要一个遍历指针。
 */
public class RotateList {

    public static void main(String[] args) {

    }

    public static ListNode rotateList(ListNode head, int k){
        if(head == null || head.next == null){
            return head;
        }

        ListNode p1 = head, p2 = head;
        int listLen = 1;
        // get length of list
        while(p1.next != null){
            p1 = p1.next;
            listLen++;
        }
        p1 = head;
        
        // p1 goes k steps first
        int rotateSteps = k % listLen;
        if(rotateSteps == 0){   // no need to rotate
            return head;
        }
        while(rotateSteps > 0){
            p1 = p1.next;
        }
        
        // find break point
        while(p1.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // rotate
        p1.next = head;
        head = p2.next;
        p2.next = null;
        
        return head;
    }
}
