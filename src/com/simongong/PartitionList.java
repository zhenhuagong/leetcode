package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

思路：
扫描原序列，记录成两条子序列，需要保存各自的头尾节点，因此需要四个指针。再外加一个扫描指针。
一路扫，一路更新小序列尾指针或大序列尾指针。
cur扫完原始链表之后，就划分好了。然后把小序列的尾节点与大序列的头结点连起来。

 */
public class PartitionList {

    public static void main(String[] args) {

    }

    public static ListNode partition(ListNode head, int x){
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode smallTail = dummy, cur = head;
        smallTail.next = cur;
        
        ListNode bigDummy = new ListNode(0);
        ListNode bigTail = bigDummy;
        
        while(cur != null){
            if (cur.val >= x) { // break big nodes and link them to big list
                smallTail.next = cur.next;
                
                bigTail.next = cur;
                cur.next = null;
                bigTail = cur;
            }else {
                smallTail = smallTail.next;
            }
            cur = smallTail.next;
        }
        smallTail.next = bigDummy.next;
        return dummy.next;
    }
}
