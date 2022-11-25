package com.lc.algorithm.binarysearch;

/**
 * 题目：寻找峰值
 * 描述：
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 分析：
 * 1.局部最大/小问题
 * 2.此题少个条件，任意两个相邻的数不相等
 * 链接：https://leetcode.cn/problems/find-peak-element
 * @author gujixian
 * @since 2022/7/14
 */
public class LC0162FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int left = 1;
        int right = nums.length - 2;
        while (left <= right) {
            int middle = left + ((right - left) >>> 1);
            if (nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]) {
                return middle;
            }
            if (nums[middle] > nums[middle + 1]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
