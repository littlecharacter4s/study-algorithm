package com.lc.algorithm.binarysearch;

/**
 * 题目：有序数组中的单一元素
 * 描述：
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * 分析：
 * 1.如果时间复杂度为O(n)，则可以用位运算^实现
 * 2.将相邻的两个数组成数对分析，下面试着找出其中的规律：数组中的数字每两个分成一组，最初的若干组的两个数字都是相同的。但遇到了只出现一次的数字之后，
 *   情况发生变化。这个只出现一次的数字和后面的数字结合成一组，导致后面所有出现两次的数字都被分到两个不同的组，即后面所有组的两个数字都不相同。
 *   这就转换成了“在一个有序数组中找找>=某个数最左侧的位置”问题
 * 链接：https://leetcode.cn/problems/single-element-in-a-sorted-array
 * @author gujixian
 * @since 2022/7/15
 */
public class LC0540SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        if ((nums.length & 1) == 0) {
            return nums[-1];
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int middle = left + ((right - left) >>> 1);

            // if ((middle & 1) == 0) {
            //     // 下标为偶数
            //     if (nums[middle] == nums[middle + 1]) {
            //         left = middle + 1;
            //     } else {
            //         right = middle;
            //     }
            // } else {
            //     // 下标为奇数
            //     if (nums[middle] == nums[middle - 1]) {
            //         left = middle + 1;
            //     } else {
            //         right = middle;
            //     }
            // }

            // (middle ^ 1)来代替上面的奇偶判断：如果middle是偶数，那么middle ^ 1 == middle + 1，否则middle ^ 1 == middle - 1
            if (nums[middle] == nums[middle ^ 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return nums[left];
    }
}
