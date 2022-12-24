package com.lc.algorithm.sort;

import java.util.Random;

/**
 * 题目：数组中的第K大元素
 *
 * 描述：
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 分析：快排剪枝改写
 *
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array/
 *
 * @author gujixian
 * @since 2022/12/24
 */
public class LC0215KthLargestElementInAnArray {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        // 6,[5],4,3,2,1
        System.out.println(new LC0215KthLargestElementInAnArray().findKthLargest(nums, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        return quickSortV1(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * 迭代版
     */
    private int quickSortV1(int[] nums, int left, int right, int k) {
        while (left < right) {
            int[] equal = this.partition(nums, left, right);
            if (equal[0] + 1 <= k && k <= equal[1] - 1) { // 等于区域包含第k大的数
                return nums[k];
            } else if (equal[0] + 1 > k) { // 第k大的数在小于区域
                right = equal[0];
            } else { // 第k大的数在大于区域
                left = equal[1];
            }
        }
        return nums[left];
    }

    /**
     * 递归版
     */
    private int quickSortV2(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int[] equal = this.partition(nums, left, right);
        if (equal[0] + 1 <= k && k <= equal[1] - 1) { // 等于区域包含第k大的数
            return nums[k];
        } else if (equal[0] + 1 > k) { // 第k大的数在小于区域
            return this.quickSortV2(nums, left, equal[0], k);
        } else { // 第k大的数在大于区域
            return this.quickSortV2(nums, equal[1], right, k);
        }
    }

    private int[] partition(int[] nums, int left, int right) {
        int pivot = nums[left + RANDOM.nextInt(right - left + 1)];
        int i = left;
        int l = i - 1;
        int r = right + 1;
        while (i < r) {
            if (nums[i] > pivot) {
                this.swap(nums, ++l, i++);
            } else if (nums[i] < pivot) {
                this.swap(nums, i, --r);
            } else {
                i++;
            }
        }
        return new int[]{l, i};
    }

    private void swap(int[] nums, int i, int j) {
        // 判断同一位置不交换很重要，数组同一个位置交换会抹成 0 -> 要想同一个位置也交换就用最常用的 -> 引入一个额外变量
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
