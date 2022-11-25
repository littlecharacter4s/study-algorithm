package com.lc.structure.linear.chain;

/**
 * 题目：环形链表 II
 * 描述：给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
 * 分析：
 * -> 假设快指针是慢指针的2倍
 * -> 2(a+b) = a + b + n(b+c)
 * -> a = (n-1)(b+c) + c
 * -> 注意到b+c恰好为环的长度，故可以推出，如果相遇时将两个指针分别放在起始位置①和相遇位置③，并以相同速度前进，当一个指针走完距离a时，另一个指针恰好走出 绕环n-1圈加上c的距离。故两指针会在环开始位置②相遇。
 * ①-----a-----②---b---③
 *              \      /
 *               \    /
 *                  c
 * 链接：https://leetcode.cn/problems/linked-list-cycle-ii/
 * @author gujixian
 * @since 2022/7/18
 */
public class LC0142LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode node = head;
                while (node != slow) {
                    node = node.next;
                    slow = slow.next;
                }
                return node;
            }
        }
        return null;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
