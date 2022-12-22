package com.lc.structure.linear.stack;

import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 题目：柱状图中最大的矩形
 * 描述：
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 分析：单调栈问题
 * 链接：https://leetcode.cn/problems/largest-rectangle-in-histogram/
 *
 * @author gujixian
 * @since 2022/12/22
 */
public class LC0084LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(new LC0084LargestRectangleInHistogram().largestRectangleArea(heights));
        System.out.println(new LC0084LargestRectangleInHistogram().largestRectangleAreaV1(heights));
        System.out.println(new LC0084LargestRectangleInHistogram().largestRectangleAreaV2(heights));
    }
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        MonotonicStack<Integer> stack = new MonotonicStack<>((o1, o2) -> o1 - o2, len);
        List<MonotonicStack.Node<Integer>> featureList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            List<MonotonicStack.Node<Integer>> subFeatureList = stack.push(new MonotonicStack.Node<>(i, heights[i]));
            if (CollectionUtils.isNotEmpty(subFeatureList)) {
                featureList.addAll(subFeatureList);
            }
        }
        int max = 0;
        for (MonotonicStack.Node<Integer> feature : featureList) {
            max = Math.max(max, (feature.getRight() - feature.getLeft() - 1) * feature.getValue());
        }
        return max;
    }

    public int largestRectangleAreaV1(int[] heights) {
        int len = heights.length;
        MonotonicStack<Integer> stack = new MonotonicStack<>((o1, o2) -> o1 - o2, len);
        List<MonotonicStack.Node<Integer>> featureList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            List<MonotonicStack.Node<Integer>> subFeatureList = stack.push(new MonotonicStack.Node<>(i, heights[i]));
            if (CollectionUtils.isNotEmpty(subFeatureList)) {
                featureList.addAll(subFeatureList);
            }
        }
        int max = 0;
        for (MonotonicStack.Node<Integer> feature : featureList) {
            max = Math.max(max, (feature.getRight() - feature.getLeft() - 1) * feature.getValue());
        }
        return max;
    }

    public int largestRectangleAreaV2(int[] heights) {
        int len = heights.length;
        MonotonicStackForInterview<Integer> stack = new MonotonicStackForInterview<>((o1, o2) -> o1 - o2, len);
        Map<Integer, Pair<Integer, Integer>> featureMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Map<Integer, Pair<Integer, Integer>> subFeatureMap = stack.push(i, heights[i]);
            subFeatureMap.forEach(featureMap::put);
        }
        int max = 0;
        for (Map.Entry<Integer, Pair<Integer, Integer>> feature : featureMap.entrySet()) {
            max = Math.max(max, (feature.getValue().getValue() - feature.getValue().getKey() - 1) * heights[feature.getKey()]);
        }
        return max;
    }
}
