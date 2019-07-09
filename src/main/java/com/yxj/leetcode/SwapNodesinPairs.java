package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio  两两交换链表中的节点
 * @create:2019-04-11 16:55
 */
public class SwapNodesinPairs {


    public ListNode swapPairs(ListNode head) {

        if(head == null){
            return null;
        }
        ListNode node = head;
        ListNode next = node.next;

        ListNode prev = head;
        if (next == null) {
            return head;
        } else {
            head = next;
            node.next = next.next;
            head.next = node;
            prev = node;
            node = node.next;
        }
        while (node != null) {
            if (node.next == null) {
                break;
            }
            next = node.next;
            node.next = next.next;
            next.next = node;
            prev.next = next;
            prev = node;
            node = node.next;
        }
        return head;

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
