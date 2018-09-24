package com.lc.algorithm;

import org.junit.Test;

public class N0009PalindromeNumberTest {
    @Test
    public void testIsPalindrome() throws Exception {
        N0009PalindromeNumber npn = new N0009PalindromeNumber();
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