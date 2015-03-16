package com.simongong;

import com.simongong.extendedtype.ListNode;

/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

思路：
经典思路，单步p1和两步p2两个指针跑，如果相遇这记录相遇地点；如果双步指针到达null，返回null
相遇时p2比p1多跑了k圈环，假设相遇点为C
假设起点A到环的起点B的距离是a；
B到C的距离为b，那么相遇的时候
Lp1 = a + b
Lp2 = a + b + kZ (Z为环的周长)
而p2的速度为p1的两倍，也就是说：Lp2 = 2 * Lp1 => a+b = kZ
我们需要求a，由上可以推导出：a = kZ - b = (n-1)Z + Z - b
Z-b就是相遇时还剩余的一环的另一半。也就是说，a相当于p1和p2在相遇点继续往前到环的起点，外加(n-1)周
那么在相遇点把其中一个拿到链表的起点去，然后两个指针以同样的速度走，一定会在环的起点相遇。
 */
public class LinkedListCycleII {

    public ListNode findCycleStart(ListNode head){
        if (head == null) {
            return null;
        }
        ListNode p1 = head, p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) { // has cycle
                break;
            }
        }
        if (!(p2.next != null && p2.next.next != null)) {   // p2 reach the end, no cycle
            return null;
        }
        
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return p1;
    }
}
