package com.yxj.arithmetic;

/**
 * @author:yuxj
 * @descriptio 两数相加
 * @create:2019/2/13 下午4:43
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        int[] a1 = new int[100];
        System.out.println(a1[9] + 1);
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode head = null;
        int add = 0;
        while (l1 != null && l2 != null) {
            if (head == null) {
                head = result;
            }else{
                ListNode result2 = result;
                result = new ListNode(0);
                result2.next = result;
            }
            int num= l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
            if (num + add < 10) {
                result.val = num + add;
                add = 0;
            } else {
                result.val = num + add - 10;
                add = 1;
            }

        }
        while (l1 != null) {
            ListNode result2 = result;
            result = new ListNode(0);
            result2.next = result;
            int num = l1.val;
            l1 = l1.next;
            if (num + add < 10) {
                result.val = num + add;
                add = 0;
            } else {
                result.val = num + add - 10;
                add = 1;
            }
        }
        while (l2 != null) {
            ListNode result2 = result;
            result = new ListNode(0);
            result2.next = result;
            int num = l2.val;
            l2 = l2.next;
            if (num + add < 10) {
                result.val = num + add;
                add = 0;
            } else {
                result.val = num + add - 10;
                add = 1;
            }
        }
        if(add == 1){
            ListNode result2 = result;
            result = new ListNode(0);
            result2.next = result;
            result.val = add;
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



