package com.lc.structure.zother.string;

/**
 * 题目：最长回文子串
 *
 * 描述：
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * 分析：Manacher 算法
 *
 * 链接：https://leetcode.cn/problems/longest-palindromic-substring/
 *
 * @author gujixian
 * @since 2022/12/24
 */
public class LC0005LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        new LC0005LongestPalindromicSubstring().longestPalindrome(s);
    }

    public String longestPalindrome(String s) {
        System.out.println("暴力解：" + this.longestPalindromeViolent(s));
        System.out.println("Manacher解：" + this.longestPalindromeManacher(s));
        return null;
    }

    public String longestPalindromeViolent(String s) {
        if (s == null) {
            return null;
        }
        char[] cs = s.toCharArray();

        int index1 = 0;
        int max1 = 0;
        int index2 = 0;
        int max2 = 0;
        for (int i = 0; i < cs.length; i++) {
            // 回文中心为 i
            int count1 = count(cs, i, i);
            if (count1 > max1) {
                max1 = count1;
                index1 = i;
            }
            // 回文中心为 i 和 (i+1) 中间
            int count2 = count(cs, i, i + 1);
            if (count2 > max2) {
                max2 = count2;
                index2 = i;
            }
        }
        int left = 0;
        int right = 0;
        if (max1 > max2) {
            left = index1 - max1 + 1;
            right = index1 + max1 - 1;
        } else {
            left = index2 - max2 + 1;
            right = index2 + max2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(cs[i]);

        }
        return sb.toString();
    }

    private int count(char[] cs, int left, int right) {
        int count = 0;
        while (left >= 0 && right < cs.length && cs[left] == cs[right]) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public String longestPalindromeManacher(String s) {
        if (s == null) {
            return null;
        }
        Manacher manacher = new Manacher();
        int[] radius = manacher.manacher(s);
        int index = 0;
        int max = 0;
        for (int i = 0; i < radius.length; i++) {
            if (radius[i] > max) {
                max = radius[i];
                index = i;
            }
        }
        char[] cs = manacher.manacherString(s);
        int left = index - max + 1;
        int right = index + max - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            if (cs[i] != '#') {
                sb.append(cs[i]);
            }
        }
        return sb.toString();
    }
}
