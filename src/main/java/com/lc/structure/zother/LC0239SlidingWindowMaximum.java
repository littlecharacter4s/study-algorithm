package com.lc.structure.zother;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

/**
 * 题目：滑动窗口最大值
 * 描述：
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * 分析：使用双端队列实现滑动窗口 -> 并没有想象的那么简单，练熟！！！
 * 链接：https://leetcode.cn/problems/sliding-window-maximum/description/
 * @author gujixian
 * @since 2022/12/22
 */
public class LC0239SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(JSON.toJSONString(new LC0239SlidingWindowMaximum().maxSlidingWindow(nums, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];
        SlidingWindow<Integer> window = new SlidingWindow<>((o1, o2) -> o2 - o1, k);
        int index = 0;
        for (int i = 0; i < len; i++) {
            SlidingWindow.Node<Integer> feature = window.slide(new SlidingWindow.Node<>(i, nums[i]));
            if (Objects.nonNull(feature)) {
                result[index++] = feature.getElement();
            }
        }
        return result;
    }
}
