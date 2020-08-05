package com.lc.algorithm;

public class LC0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode before = null;
        ListNode current = head;
        ListNode after;
        while (current != null) {
            after = current.next;
            current.next = before;
            before = current;
            current = after;
        }
        return before;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
