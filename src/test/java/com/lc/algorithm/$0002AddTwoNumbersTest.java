package com.lc.algorithm;

import org.junit.Test;

public class $0002AddTwoNumbersTest {
    @Test
    public void testAddTwoNumbers() throws Exception {
        $0002AddTwoNumbers.ListNode l1,f1;
        l1 = f1 = new $0002AddTwoNumbers.ListNode(5);
        f1.next = new $0002AddTwoNumbers.ListNode(6);
        f1 = f1.next;
        f1.next = new $0002AddTwoNumbers.ListNode(3);
        f1 = f1.next;
        f1.next = new $0002AddTwoNumbers.ListNode(2);
        f1 = l1;
        while (f1 != null) {
            System.out.print(f1.val);
            f1 = f1.next;
        }
        System.out.println("\n---------------------");

        $0002AddTwoNumbers.ListNode l2 = new $0002AddTwoNumbers.ListNode(7);
        $0002AddTwoNumbers.ListNode f2 = l2;
        f2.next = new $0002AddTwoNumbers.ListNode(4);
        f2 = f2.next;
        f2.next = new $0002AddTwoNumbers.ListNode(5);
        f2 = l2;
        while (f2 != null) {
            System.out.print(f2.val);
            f2 = f2.next;
        }
        System.out.println("\n---------------------");

        $0002AddTwoNumbers.ListNode heard = new $0002AddTwoNumbers().addTwoNumbers(l1, l2);
        while (heard != null) {
            System.out.print(heard.val);
            heard = heard.next;
        }
    }
}