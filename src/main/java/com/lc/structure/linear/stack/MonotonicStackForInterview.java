package com.lc.structure.linear.stack;

import com.alibaba.fastjson.JSON;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * 单调栈
 *
 * @author gujixian
 * @since 2022/12/22
 */
public class MonotonicStackForInterview<V> {
    // Pair：值 -> 索引队列
    private final Deque<Pair<V, Deque<Integer>>> stack = new LinkedList<>();
    private final Comparator<V> comparator;
    private final int capacity;
    private int size;

    public MonotonicStackForInterview(Comparator<V> comparator, int capacity) {
        this.comparator = comparator;
        this.capacity = capacity;
    }

    public Map<Integer, Pair<Integer, Integer>> push (int i, V v) {
        Map<Integer, Pair<Integer, Integer>> featureMap = new HashMap<>();
        while (!stack.isEmpty() && comparator.compare(stack.peekLast().getKey(), v) > 0) {
            Pair<V, Deque<Integer>> element = stack.pollLast();
            int left = stack.isEmpty() ? -1 : stack.peekLast().getValue().peekLast();
            for (Integer index : element.getValue()) {
                featureMap.put(index, new Pair<>(left, i));
            }
        }
        if (!stack.isEmpty() && comparator.compare(stack.peekLast().getKey(), v) == 0) {
            stack.peekLast().getValue().offerLast(i);
        } else {
            Deque<Integer> indexQueue = new LinkedList<>();
            indexQueue.offerLast(i);
            stack.offerLast(new Pair<>(v, indexQueue));
        }
        size++;
        // 数组遍历完了，对栈中剩余的每个元素求特征值
        if (size == capacity) {
            while (!stack.isEmpty()) {
                Pair<V, Deque<Integer>> element = stack.pollLast();
                int left = stack.isEmpty() ? -1 : stack.peekLast().getValue().peekLast();
                for (Integer index : element.getValue()) {
                    featureMap.put(index, new Pair<>(left, capacity));
                }
            }
        }
        return featureMap;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,5,6,2,3};
        MonotonicStackForInterview<Integer> monotonicStack = new MonotonicStackForInterview<>((o1, o2) -> o1 - o2, nums.length);
        Map<Integer, Pair<Integer, Integer>> featureMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Pair<Integer, Integer>> subFeatureMap = monotonicStack.push(i, nums[i]);
            if (!subFeatureMap.isEmpty()) {
                subFeatureMap.forEach(featureMap::put);
            }
        }
        System.out.println(JSON.toJSONString(featureMap));
    }
}
