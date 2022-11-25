package com.lc.structure.linear.chain;

/**
 * 题目：相交链表
 * 描述：给你两个无环单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
 * 分析：
 * 1.若两个链表相交，则尾节点一定相等
 * 2.长链表先走两个链表长度差节点，然后两个链表同时走，并逐个比对，第一个相等的节点就是相交节点
 * 链接：https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * @author gujixian
 * @since 2022/7/18
 */
public class LC0160IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lengthA = 1;
        int lengthB = 1;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA.next != null) {
            nodeA = nodeA.next;
            lengthA++;
        }
        while (nodeB.next != null) {
            nodeB = nodeB.next;
            lengthB++;
        }
        if (nodeA != nodeB) {
            return null;
        }
        nodeA = headA;
        nodeB = headB;
        int length = Math.abs(lengthA - lengthB);
        // System.out.print(length);
        if (lengthA > lengthB) {
            for (int i = 0; i < length; i++) {
                nodeA = nodeA.next;
            }
        } else {
            for (int i = 0; i < length; i++) {
                nodeB = nodeB.next;
            }
        }
        while (nodeA != nodeB) {
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return nodeA;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
