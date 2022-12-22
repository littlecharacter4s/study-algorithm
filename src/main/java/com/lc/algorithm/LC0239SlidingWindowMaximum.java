package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import com.lc.structure.zother.SlidingWindow;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
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
