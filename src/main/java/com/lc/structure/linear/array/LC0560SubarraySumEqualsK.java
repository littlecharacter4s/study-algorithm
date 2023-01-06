package com.lc.structure.linear.array;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：和为 K 的子数组
 * 描述：给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * 分析：
 * 1.前缀和分析
 *   s(i)代表子数组arr[0..i]所有元素的累加和。
 *   那么子数组arr[j..i] (0≤j≤i<arr.length) 的累加和为s(i)-s(j-1)，这个结论是求解这道题的核心。
 *   子数组arr[j..i]=k,就是和为 k 的连续子数组，这就转化为求一个数组中两个数的差为 k 的个数！
 * 2.转换为“数组中两个数的差为 k”问题
 * 链接：https://leetcode.cn/problems/subarray-sum-equals-k
 * @author gujixian
 * @since 2022/7/26
 */
public class LC0560SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        if (nums == null || nums.length == 0) {
            return count;
        }
        Map<Integer, Pair<Integer, Integer>> sumMap = new HashMap<>();
        // 前0项和为0，Pair的key为前n项、value为前n项和为0出现的次数
        sumMap.put(0, new Pair<>(0, 1));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumMap.containsKey(sum - k)) {
                System.out.println("前" + sumMap.get(sum - k).getKey() + "项和 与 前" + (i + 1) + "项和 的 差为k，" +
                        "即连续子数组[" + (sumMap.get(sum - k).getKey()) + "~" + i + "]的和为k");
                count += sumMap.get(sum - k).getValue();
            }
            // 前n项和，n是从1开始，而下标是从0开始，故+1
            sumMap.put(sum, new Pair<>(i + 1, sumMap.getOrDefault(sum, new Pair<>(0, 0)).getValue() + 1));
        }
        return count;
    }

    public static void main(String[] args) {
        LC0560SubarraySumEqualsK subarraySumEqualsK = new LC0560SubarraySumEqualsK();
        int[] nums = {1,2,3,3};
        subarraySumEqualsK.subarraySum(nums, 6);
    }
}
