package com.lc.algorithm.zlc;

import java.util.PriorityQueue;

public class LC0703KthLargestElementInAStream {
    private int k;
    private PriorityQueue<Integer> minHeap;
    public LC0703KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k, (o1, o2) -> o1-o2);
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
                continue;
            }
            if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
    }
    public Integer add(int val) {
        // 堆没满时
        if (minHeap.size() < k) {
            minHeap.offer(val);
            if (minHeap.size() < k) {
                return null;
            }
            return minHeap.peek();
        }
        // 堆满时 -> 判断是否替换堆顶元素
        if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}
