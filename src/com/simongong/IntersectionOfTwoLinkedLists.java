package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

思路：观察A和B，如果可以把两个链表弄成一样的长度，那么一齐遍历，当两个游标的值一样的时候，就是交点了。
有两种办法：
1. 先各扫一遍，获得两个链表的长度，然后将长的链条向前移动差值（len1 - len2），再开始遍历。
2. 各扫一遍，然后在A的尾节点把curA链接到B的头，在B的尾节点把CurB链接到A的头，这样做的本质上就是补齐了listA和listB的长度差距。
继续往前扫，这一回开始检查curA==curB的点，即为交点。
注意，为了避免无限循环，这种方法需要判断链表是否真的相交，因此要保留二者的尾节点。若尾节点不相等，则不相交，返回null。
 */
public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {

    }

    public static ListNode intersectTwoList(ListNode listA, ListNode listB){
        if (listA == null || listB == null) {
            return null;
        }
        
        ListNode curA = listA, curB = listB;
        int lengthA = 1, lengthB = 1;
        while (curA.next != null) {
            lengthA++;
        }
        while (curB.next != null) {
            lengthB++;
        }
        
        int offset = Math.abs(lengthA - lengthB);
        curA = listA;
        curB = listB;
        if (lengthA > lengthB) {
            while(offset > 0){
                curA = curA.next;
                offset--;
            }
        }else{
            while (offset > 0) {
                curB = curB.next;
                offset--;
            }
        }
        
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
    
    public static ListNode intersectTwoList2(ListNode listA, ListNode listB){
        if (listA == null || listB == null) {
            return null;
        }
        
        ListNode curA = listA, curB = listB;
        ListNode tailA = null, tailB = null;
        while (true) {
            if (curA.next == null) {
                tailA = curA;
                curA.next = listB;
            }
            if (curB.next == null) {
                tailB = curB;
                curB.next = listA;
            }
            if (tailA != null && tailB != null && tailA != tailB) { // no intersection between listA and listB
                return null;
            }
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
    }
}
