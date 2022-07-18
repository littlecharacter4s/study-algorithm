package com.lc.algorithm.structure.chain;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gujixian
 * @since 2022/7/18
 */
public class NC0001SplitLinkedListTest {
    @Test
    public void TestSplit() {
        NC0001SplitLinkedList splitLinkedList = new NC0001SplitLinkedList();
        NC0001SplitLinkedList.ListNode node1 = new NC0001SplitLinkedList.ListNode(1);
        NC0001SplitLinkedList.ListNode node2 = new NC0001SplitLinkedList.ListNode(2);
        NC0001SplitLinkedList.ListNode node3 = new NC0001SplitLinkedList.ListNode(3);
        NC0001SplitLinkedList.ListNode node4 = new NC0001SplitLinkedList.ListNode(4);
        // NC0001SplitLinkedList.ListNode node5 = new NC0001SplitLinkedList.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        // node4.next = node5;
        System.out.println("原链表：");
        NC0001SplitLinkedList.ListNode head = node1;
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        NC0001SplitLinkedList.ListNode[] splitNodes = splitLinkedList.split(node1);
        for (NC0001SplitLinkedList.ListNode node : splitNodes) {
            System.out.println("子链表：");
            while(node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }
    }

}