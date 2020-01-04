package com.lc.algorithm;

/**
 * 回文数：正读、反读都是一样的（左右对称的）
 * 思路：通过取整和取余操作，从两端逐渐缩小待判断的数
 */
public class LC0009PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}
