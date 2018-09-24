package com.lc.algorithm;

/**
 * 思路：动态规划，采用中心扩张的方法
 * 1、长度为n的字符串有2*n-1个回环中心（字符+字符间隙）
 * 2、将回环中心依次编号遍历，设立left和right两个指针
 * 3、从回环中心开始，依次向外扩张（left -= 1 && right += 1）
 * 4、若s[left]==s[right]且left和right在0到n的范围内，则回环个数加一，并继续向外扩张，直到不满足条件
 */
public class N0647PalindromicSubstrings {
    public int countSubstrings(String s) {
        int count = 0;
        char[] c = s.toCharArray();
        int length = s.length();
        for (int center = 0; center < 2 * length - 1; center++) {
            // 慢在left和right的计算
            int left = center >>> 1;
            int right = left + (center % 2);
            while (left >= 0 && right < length && c[left] == c[right]) {
                count++;
                left--;
                right++;
            }
        }
        return count;
    }

    public int countSubstringsEfficient(String s) {
        int count = 0;
        char[] c = s.toCharArray();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            count += count(c, i, i);
            count += count(c, i, i + 1);
        }
        return count;
    }

    private int count(char[] c, int left, int right) {
        int count = 0;
        while (left >= 0 && right < c.length && c[left] == c[right]) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}