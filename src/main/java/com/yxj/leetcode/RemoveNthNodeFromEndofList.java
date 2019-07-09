package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio 删除链表的倒数第N个节点
 * @create:2019-04-11 16:03
 */
public class RemoveNthNodeFromEndofList {

    public static void main(String[] args) {
        RemoveNthNodeFromEndofList removeNthNodeFromEndofList = new RemoveNthNodeFromEndofList();
        removeNthNodeFromEndofList.test();
    }


    public  void test(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;

        print(removeNthFromEnd(l1,1));
    }

    public void print(ListNode head){
        while (head != null){
            System.out.print(head.val);
            head = head.next;
        }

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        int index = 0;
        ListNode node = head;
        ListNode prev = head;
        while (node.next != null){
           if(index++>=n){
               prev = prev.next;
           }
           node = node.next;
        }

        if(++index<=n){
            return head.next;
        }
        prev.next = prev.next.next;
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



