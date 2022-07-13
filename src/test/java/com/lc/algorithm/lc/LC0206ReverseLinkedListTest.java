package com.lc.algorithm.lc;

import com.lc.algorithm.lc.LC0206ReverseLinkedList;
import org.junit.Test;

public class LC0206ReverseLinkedListTest {
    int size = 6;
    LC0206ReverseLinkedList lc0206 = new LC0206ReverseLinkedList();

    @Test
    public void testReverseList() throws Exception {
        LC0206ReverseLinkedList.ListNode root = new LC0206ReverseLinkedList.ListNode(1);
        LC0206ReverseLinkedList.ListNode ln1 = root;
        LC0206ReverseLinkedList.ListNode ln2;
        for (int i = 2; i <= size; i++) {
            ln2 = new LC0206ReverseLinkedList.ListNode(i);
            ln1.next = ln2;
            ln1 = ln2;
        }
        root = lc0206.reverseList(root);
        for (int j = 0; j < size; j++) {
            System.out.println(root.val);
            root = root.next;
        }
    }

}