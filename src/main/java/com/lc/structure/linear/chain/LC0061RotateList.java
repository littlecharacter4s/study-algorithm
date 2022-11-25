package com.lc.structure.linear.chain;

/**
 * 题目：旋转链表
 * 描述：给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
 * 分析：
 * 1.找到新的头节点：x = size - (k % size)
 * 2.将尾节点链接到原来的头节点，将新头节点的前一个节点的next指向null
 * 链接：https://leetcode.cn/problems/rotate-list/
 * @author gujixian
 * @since 2022/7/19
 */
public class LC0061RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }
        int x = size - (k % size);
        if (x == size) {
            return head;
        }
        ListNode prev = null;
        ListNode hn = head;
        for (int i = 0; i < x; i++) {
            prev = hn;
            hn = hn.next;
        }
        prev.next = null;
        tail.next = head;
        return hn;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
