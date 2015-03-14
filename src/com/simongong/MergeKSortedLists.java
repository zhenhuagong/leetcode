package com.simongong;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.simongong.extendedtype.ListNode;

/*
Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.

思路：
1. 二分法。对k不断二分成k1,k2，当k1=2时，采用mergeSort合并并返回结果。最终获得k条链表的合并结果。
k条list，每条长度为n，那么二分的时间复杂度为log(k)，也就是会对结果合并这么多次。而每次结果的合并的时间复杂度为{k/2*mergesort(n)，k/4*mergesort(2n), ..., mergesort(k/2*n) }。
而O(mergesort(n))=O(nlgn)，累加上面各项得出总的时间复杂度为：O(nklogk)
实现上，分两步走：
1) 二分逻辑divide(lists, left, right)，当二分到终点时，返回lists[left]
2) mergesort逻辑，正常的合并两条链表

2. 借鉴选择排序的思想，每次选出当前剩余结点中最小的节点。同时考虑到链表都是排好序的，每次只需要比较k条链表的头结点。
由于每次都是从k个节点中选择最小的节点。因此，可以用一个大小为k的堆，来实现这个选择操作。当遍历完k条链表之后，选择排序也就完成了。
这个时间复杂度很清晰，每次堆的调整复杂度为O(logk)，遍历全部节点的复杂度为O(nk)，因此总复杂度就是O(nklogk)
实现上：
Java中的PriorityQueue就是堆的一种实现，给它定义一个comparator就可以了。
 */
public class MergeKSortedLists {

    public ListNode mergeKLists2(List<ListNode> lists){
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        int numOfLists = lists.size();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(numOfLists, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // initial the heap
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            tail.next = cur;
            tail = tail.next;
            
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }
        
        return dummy.next;
    }

    public ListNode mergeKLists(List<ListNode> lists){
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return divideMergeK(lists, 0, lists.size() - 1);
    }
    
    private ListNode divideMergeK(List<ListNode> lists, int left, int right){
        // keep divide lists by 2
        if (left < right) {
            int mid = left + (right - left) / 2;
            return merge(divideMergeK(lists, left, mid), divideMergeK(lists, mid+1, right));
        }
        // the left list is the only one left after dividing reach the end
        return lists.get(left);
    }
    
    private ListNode merge(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            }else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        
        if (head1 == null) {
            cur.next = head2;
        }else {
            cur.next = head1;
        }
        
        return dummy.next;
    }
}
