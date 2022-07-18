package com.lc.algorithm.structure.chain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gujixian
 * @since 2022/7/17
 */
public class LC0234PalindromeLinkedListTest {
    @Test
    public void testIsPalindrome() {
        LC0234PalindromeLinkedList palindromeLinkedList = new LC0234PalindromeLinkedList();
        LC0234PalindromeLinkedList.ListNode node1 = new LC0234PalindromeLinkedList.ListNode(1);
        LC0234PalindromeLinkedList.ListNode node2 = new LC0234PalindromeLinkedList.ListNode(2);
        LC0234PalindromeLinkedList.ListNode node3 = new LC0234PalindromeLinkedList.ListNode(2);
        LC0234PalindromeLinkedList.ListNode node4 = new LC0234PalindromeLinkedList.ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(palindromeLinkedList.isPalindrome(node1));
    }

}