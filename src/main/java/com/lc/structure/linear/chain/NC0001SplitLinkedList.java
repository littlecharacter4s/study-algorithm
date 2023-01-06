package com.lc.structure.linear.chain;

/**
 * 题目：拆分链表
 * 描述：将一个链表按照奇偶拆成两个量表
 * 分析：链表拆分问题
 * @author gujixian
 * @since 2022/7/18
 */
public class NC0001SplitLinkedList {
    public static void main(String[] args) {
        NC0001SplitLinkedList splitLinkedList = new NC0001SplitLinkedList();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        // ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        // node4.next = node5;
        System.out.println("原链表：");
        ListNode head = node1;
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        ListNode[] splitNodes = splitLinkedList.split(node1);
        for (ListNode node : splitNodes) {
            System.out.println("子链表：");
            while(node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }    
    }
    
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

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
