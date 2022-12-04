package com.lc.structure.linear.chain;

/**
 * 题目：K 个一组翻转链表
 * 描述：
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 分析：分组调度 + 链表翻转
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * @author gujixian
 * @since 2022/12/3
 */
public class LC0025ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        for (int i = 2; i < 6; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        ListNode nodex = new LC0025ReverseNodesInKGroup().reverseKGroup(head, 2);
        while (nodex != null) {
            System.out.print(nodex.val + " ");
            nodex = nodex.next;
        }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode groupTail = this.getGroupEnd(head, k);
        if (groupTail == null || k <= 1) {
            return head;
        }
        ListNode result = groupTail;
        // 第一组比较特殊，预先处理
        ListNode groupHead = head;
        ListNode nextHead = groupTail.next;
        this.reverse(groupHead, groupTail);
        // 从第二组开始，循环处理
        groupTail = this.getGroupEnd(nextHead, k);
        while (groupTail != null) {
            groupHead.next = groupTail;
            groupHead = nextHead;
            nextHead = groupTail.next;
            this.reverse(groupHead, groupTail);
            groupTail = this.getGroupEnd(nextHead, k);
        }
        // 处理最后一组
        groupHead.next = nextHead;
        return result;
    }

    private ListNode getGroupEnd(ListNode node, int k) {
        // 先减一是以为 node 就是第一个
        while (--k > 0 && node != null) {
            node = node.next;
        }
        return node;
    }

    private void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = start;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
