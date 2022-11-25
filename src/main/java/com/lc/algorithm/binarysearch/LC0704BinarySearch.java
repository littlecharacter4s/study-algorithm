package com.lc.algorithm.binarysearch;

/**
 * 题目：二分查找
 * 描述：给定一个n个元素有序的（升序）整型数组nums和一个目标值target，写一个函数搜索nums中的target，如果目标值存在返回下标，否则返回 -1。
 * 分析：
 * 1.数组nums是否有重复元素
 * 2.本题要求返回一个下标，数组nums有无重复元素无所谓
 * 链接：https://leetcode.cn/problems/binary-search
 * @author gujixian
 * @since 2022-07-14
 */
public class LC0704BinarySearch {
    public int search(int[] nums, int target) {
        int result = -1;
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return result;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >>> 1);
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return result;
    }
}
