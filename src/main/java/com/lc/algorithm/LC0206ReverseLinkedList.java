package com.lc.algorithm;

public class LC0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode pro;
        while (cur != null) {
            pro = cur.next;
            cur.next = pre;
            pre = cur;
            cur = pro;
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
