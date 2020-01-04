package com.lc.algorithm;

import org.junit.Test;

public class LC0009PalindromeNumberTest {
    @Test
    public void testIsPalindrome() throws Exception {
        LC0009PalindromeNumber npn = new LC0009PalindromeNumber();
        int x;
        x = 10101;
        System.out.println(x + (npn.isPalindrome(x) ? "是" : "不是") + "回文数");
        x = 12121;
        System.out.println(x + (npn.isPalindrome(x) ? "是" : "不是") + "回文数");
        x = 12101;
        System.out.println(x + (npn.isPalindrome(x) ? "是" : "不是") + "回文数");
        x = -10101;
        System.out.println(x + (npn.isPalindrome(x) ? "是" : "不是") + "回文数");
    }
}