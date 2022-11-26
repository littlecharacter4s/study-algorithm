package com.lc.structure.linear.array;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.JsrInstruction;
import javafx.util.Pair;

import java.util.*;

/**
 * 题目：前 K 个高频元素
 * 描述：给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按任意顺序返回答案。
 * 分析：
 * 链接：https://leetcode.cn/problems/top-k-frequent-elements/description/
 *
 * @author gujixian
 * @since 2022/11/26
 */
public class LC0347TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(JSON.toJSONString(new LC0347TopKFrequentElements().topKFrequent(nums, 3)));
        System.out.println(JSON.toJSONString(new LC0347TopKFrequentElements().topKFrequentByHeap(nums, 3)));
    }

    // O(N)
    public int[] topKFrequent(int[] nums, int k) {
        // 校验数据
        if (k <= 0) {
            return null;
        }
        if (k >= nums.length) {
            return nums;
        }

        // 记录每个元素出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 构建桶：下标表示元素的出现次数，值表示出现该次数的元素集合
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        countMap.forEach((key, value) -> {
            if (buckets[value] == null) {
                buckets[value] = new ArrayList<>();
            }
            buckets[value].add(key);
        });

        // 构建结果：桶倒序，把集合中的元素往结果里赛，塞满为止
        int[] result = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] == null) {
                continue;
            }
            for (int element : buckets[i]) {
                if (index > (k - 1)) {
                    return result;
                }
                result[index++] = element;
            }
        }
        return result;
    }

    // O(N*logK)
    public int[] topKFrequentByHeap(int[] nums, int k) {
        // 校验数据
        if (k <= 0) {
            return null;
        }
        if (k >= nums.length) {
            return nums;
        }
        // 记录每个元素出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        // 构建堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2) -> o1[1] - o2[1]);
        countMap.forEach((num, count) -> {
            if (heap.size() >= k) {
                int[] top = heap.peek();
                if (top[1] < count) {
                    heap.poll();
                    heap.offer(new int[]{num, count});
                }
            } else {
                heap.offer(new int[]{num, count});
            }
        });
        // 构建结果
        int[] result = new int[k];
        int index = 0;
        for (Object o : heap.toArray()) {
            result[index++] = ((int[]) o)[0];
        }
        return result;
    }
}
