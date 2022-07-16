package com.lc.algorithm.structure.chain;

/**
 * 题目：反转链表
 * 描述：给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 分析：画图Coding
 * 链接：https://leetcode.cn/problems/reverse-linked-list/
 * @author gujixian
 * @since 2022-07-16
 */
public class LC0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 准备阶段除了当前节点，其他都是null
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
