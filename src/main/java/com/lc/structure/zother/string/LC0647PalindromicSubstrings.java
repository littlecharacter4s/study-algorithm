package com.lc.structure.zother.string;

/**
 * 题目：回文子串数量
 *
 * 描述：
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 分析：
 * 一、暴力解
 * 1、长度为n的字符串有2*n-1个回环中心（字符+字符间隙）
 * 2、将回环中心依次编号遍历，设立left和right两个指针
 * 3、从回环中心开始，依次向外扩张（left -= 1 && right += 1）
 * 4、若s[left]==s[right]且left和right在0到n的范围内，则回环个数加一，并继续向外扩张，直到不满足条件
 * 二、Manacher 算法
 *
 * 链接：https://leetcode.cn/problems/palindromic-substrings/
 */
public class LC0647PalindromicSubstrings {
    public static void main(String[] args) {
        String s = "xabcbastsabcbay";
        System.out.println("暴力解：" + new LC0647PalindromicSubstrings().countSubstrings(s));
        int[] radius = new Manacher().manacher(s);
        int result = 0;
        for (int r : radius) {
            result += r / 2;
        }
        System.out.println("Manacher解：" + result);
    }

    public int countSubstrings(String s) {
        int count = 0;
        char[] c = s.toCharArray();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 回文中心为 i
            count += count(c, i, i);
            // 回文中心为 i 和 (i+1) 中间
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