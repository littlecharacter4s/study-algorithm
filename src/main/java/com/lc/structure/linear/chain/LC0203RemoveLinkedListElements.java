package com.lc.structure.linear.chain;

/**
 * 题目：移除链表元素
 * 描述：给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
 * 分析：
 * 1.删除链表元素问题
 * 2.画图+使用哨兵节点
 * 链接：https://leetcode.cn/problems/remove-linked-list-elements/
 * @author gujixian
 * @since 2022/7/16
 */
public class LC0203RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0, head);
        ListNode node = sentinel;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return sentinel.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
