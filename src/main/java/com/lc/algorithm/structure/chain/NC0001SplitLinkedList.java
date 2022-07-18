package com.lc.algorithm.structure.chain;

/**
 * 题目：拆分链表
 * 描述：将一个链表按照奇偶拆成两个量表
 * 分析：链表拆分问题
 * @author gujixian
 * @since 2022/7/18
 */
public class NC0001SplitLinkedList {
    public ListNode[] split(ListNode head) {
        ListNode[] result = new ListNode[2];
        result[0] = head;
        if (head == null || head.next == null) {
            return result;
        }
        result[1] = head.next;
        ListNode next = null;
        while (head != null && head.next != null) {
            next = head.next.next;
            head.next.next = next == null ? null : next.next;
            head.next = next;
            head = next;
        }
        return result;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
