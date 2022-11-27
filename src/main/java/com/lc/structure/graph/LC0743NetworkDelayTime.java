package com.lc.structure.graph;

import javafx.util.Pair;

import java.util.*;

/**
 * 题目：网络延迟时间
 * 描述：有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 分析：迪杰斯塔拉算法（Dijkstra 算法）
 * 链接：https://leetcode.cn/problems/network-delay-time/description/
 *
 * @author gujixian
 * @since 2022/11/28
 */
public class LC0743NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = new int[][]{new int[]{3, 2, 1}, new int[]{2, 1, 1}, new int[]{2, 3, 1}, new int[]{2, 5, 4}, new int[]{3, 4, 1}, new int[]{4, 5, 1}, new int[]{5, 3, 1}, new int[]{3, 5, 1}};
        System.out.println(new LC0743NetworkDelayTime().networkDelayTimeV1(times, 5, 2));
    }

    public int networkDelayTimeV1(int[][] times, int n, int k) {
        // 初始判断
        // 初始化延迟 Map
        Map<Integer, Integer> delayMap = new HashMap<>(n);
        Set<Integer> visitedNodes = new HashSet<>(n);
        for (int i = 1; i <= n; i++) {
            delayMap.put(i, Integer.MAX_VALUE);
            if (i == k) {
                delayMap.put(i, 0);
            }
        }

        // 计算 节点 k 都所有节点的最小延时
        Integer minDelay = getMinDelay(delayMap, visitedNodes);
        while (minDelay != null) {
            for (int[] time : times) {
                if (time[0] == minDelay) {
                    int delay = delayMap.get(time[1]);
                    if (delay == Integer.MAX_VALUE) {
                        delay = delayMap.get(minDelay) + time[2];
                    } else {
                        delay = Math.min(delay, delayMap.get(minDelay) + time[2]);
                    }
                    delayMap.put(time[1], delay);
                }
            }
            minDelay = getMinDelay(delayMap, visitedNodes);
        }
        // 构建结果
        int result = 0;
        for (Integer delay : delayMap.values()) {
            if (delay == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, delay);

        }
        return result;
    }

    private Integer getMinDelay(Map<Integer, Integer> delayMap, Set<Integer> visitedNodes) {
        Integer minNode = null;
        int minDelay = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : delayMap.entrySet()) {
            Integer node = entry.getKey();
            Integer delay = entry.getValue();
            if (delay != null && delay < minDelay && !visitedNodes.contains(node)) {
                minDelay = delay;
                minNode = node;
            }
        }
        if (minNode == null) {
            return null;
        }
        visitedNodes.add(minNode);
        return minNode;
    }

    public int networkDelayTimeV2(int[][] times, int n, int k) {
        final int MAX = Integer.MAX_VALUE / 2;
        // 用邻接表法重新表示图：原节点 -> 目标节点，权值
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int[] time : times) {
            List<Pair<Integer, Integer>> edges = graph.getOrDefault(time[0], new ArrayList<>());
            edges.add(new Pair<>(time[1], time[2]));
            graph.put(time[0], edges);
        }
        // 表示从选择的节点到所有节点（包括自己）的最小延时 + 初始化
        Map<Integer, Integer> delayMap = new HashMap<>(n);
        for (int i = 1; i <= n; i++) {
            delayMap.put(i, MAX);
        }
        delayMap.put(k, 0);
        // 堆 + 初始化
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        heap.offer(new Pair<>(k, 0));
        // 计算 节点 k 都所有节点的最小延时
        while (!heap.isEmpty()) {
            Pair<Integer, Integer> x = heap.poll();
            List<Pair<Integer, Integer>> edges = graph.get(x.getKey());
            if (edges == null) {
                continue;
            }
            for (Pair<Integer, Integer> edge : edges) {
                int delay = delayMap.get(x.getKey()) + edge.getValue();
                if (delay < delayMap.get(edge.getKey())) {
                    delayMap.put(edge.getKey(), delay);
                    heap.offer(new Pair<>(edge.getKey(), delay));
                }
            }
        }
        // 构建结果
        int result = 0;
        for (Integer delay : delayMap.values()) {
            if (delay == MAX) {
                return -1;
            }
            result = Math.max(result, delay);

        }
        return result;
    }
}
