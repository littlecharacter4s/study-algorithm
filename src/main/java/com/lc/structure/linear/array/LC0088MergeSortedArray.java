package com.lc.structure.linear.array;

/**
 * 题目：合并两个有序数组
 * 描述：
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 分析：
 * 1.使用额外空间合并两个有序数组
 * 2.逆向思维！！！把尾部的空间利用起来 -> 很巧
 * 链接：https://leetcode.cn/problems/merge-sorted-array
 * @author gujixian
 * @since 2022/7/19
 */
public class LC0088MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int x = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            nums1[x--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (i >= 0) {
            nums1[x--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[x--] = nums2[j--];
        }
    }
}
