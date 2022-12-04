package com.lc.structure.linear.chain;

public class LC0002AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode head1 = l1;
        while (head1 != null) {
            System.out.print(head1.val + " ");
            head1 = head1.next;
        }
        System.out.println();
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListNode head2 = l2;
        while (head2 != null) {
            System.out.print(head2.val + " ");
            head2 = head2.next;
        }
        System.out.println();
        ListNode sum = new LC0002AddTwoNumbers().addTwoNumbers(l1, l2);
        while (sum != null) {
            System.out.print(sum.val + " ");
            sum = sum.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        int length1 = 0;
        int length2 = 0;
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1 != null) {
            length1++;
            head1 = head1.next;
        }
        while (head2 != null) {
            length2++;
            head2 = head2.next;
        }
        // 让 head 指向较长的链表
        ListNode head = length1 > length2 ? l1 : l2;
        ListNode last = null; // 最后两个节点相加可能产生进位
        int carry = 0; // 进位
        ListNode node = head; // 最后结果由长链表修改而来
        while (node != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int v = carry + v1 + v2;
            node.val = v % 10;
            carry = v / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            last = node;
            node = node.next;
        }
        if (carry != 0) { // 最后两个节点相加产生进位
            last.next = new ListNode(carry);
        }
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
