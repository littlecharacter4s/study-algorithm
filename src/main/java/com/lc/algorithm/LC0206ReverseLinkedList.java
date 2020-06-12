package com.lc.algorithm;

public class LC0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pro = head;
        ListNode pre = null;
        ListNode next;
        while (pro != null) {
            next = pro.next;
            pro.next = pre;
            pre = pro;
            pro = next;
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
