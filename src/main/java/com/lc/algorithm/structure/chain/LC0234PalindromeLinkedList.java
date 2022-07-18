package com.lc.algorithm.structure.chain;

/**
 * 题目：回文链表
 * 描述：给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 分析：
 * 1.快慢指针找到中点或下中点，此过程中前半部分链表逆序
 * 2.处理中间边界
 * 3.判断是否回文，并还原链表
 * 链接：
 * @author gujixian
 * @since 2022/7/17
 */
public class LC0234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        ListNode prev = null;
        ListNode next = null;
        // 找到链表的中点或下中点（因为链表翻转prev滞后一个节点）
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // 快指针往前走
            fast = fast.next.next;
            // 慢指针边走边逆序
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        // 记录链表逆序的开始节点
        ListNode curr = prev;
        prev = slow;
        // 如果正中中点（即链表有奇数个节点时）
        if (fast != null) {
            slow = slow.next;
        }
        // 判断是否是回文，并将链表还原
        boolean result = true;
        while (curr != null && slow != null) {
            if (curr.val != slow.val) {
                result = false;
            }
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            slow = slow.next;

        }
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
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
