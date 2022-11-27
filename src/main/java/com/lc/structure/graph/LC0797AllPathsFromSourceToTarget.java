package com.lc.structure.graph;

import com.alibaba.fastjson.JSON;
import javafx.util.Pair;

import java.util.*;

/**
 * 题目：所有可能的路径
 * 描述：给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 * 分析：图的深度优先遍历
 * 链接：https://leetcode.cn/problems/all-paths-from-source-to-target/
 *
 * @author gujixian
 * @since 2022/11/28
 */
public class LC0797AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        int[][] a = new int[][]{new int[]{4, 3, 1}, new int[]{3, 2, 4}, new int[]{3}, new int[]{4}, new int[]{}};
        System.out.println(JSON.toJSONString(new LC0797AllPathsFromSourceToTarget().allPathsSourceTarget(a)));
    }

    List<List<Integer>> result = new ArrayList<>();
    Deque<Integer> stack = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0 || graph[0] == null || graph[0].length == 0) {
            return null;
        }
        // 用邻接表法重新表示图：原节点 -> 目标节点，权值
        Map<Integer, List<Pair<Integer, Integer>>> graphTable = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            List<Pair<Integer, Integer>> edges = graphTable.getOrDefault(i, new ArrayList<>());
            for (int node : graph[i]) {
                edges.add(new Pair<>(node, 1));
            }
            graphTable.put(i, edges);
        }
        // this.print(graphTable); // 深度优先打印所有路径
        stack.offerLast(0);
        dfs(graphTable, 0, graphTable.size() - 1);
        return result;
    }

    private void dfs(Map<Integer, List<Pair<Integer, Integer>>> graphTable, int node, int targetNode) {
        // System.out.println(x);
        if (node == targetNode) {
            result.add(new ArrayList<Integer>(stack));
            return;
        }
        for (Pair<Integer, Integer> edge : graphTable.get(node)) {
            stack.offerLast(edge.getKey());
            dfs(graphTable, edge.getKey(), targetNode);
            stack.pollLast();
        }
    }

    private void print(Map<Integer, List<Pair<Integer, Integer>>> graphTable) {
        // Set<Integer> visitedNode = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0);
        // visitedNode.add(0);
        while (!stack.isEmpty()) {
            int node = stack.pollLast();
            System.out.println(node);
            for (Pair<Integer, Integer> edge : graphTable.get(node)) {
                stack.offerLast(edge.getKey());
                // if (!visitedNode.contains(edge.getKey())) {
                //     visitedNode.add(edge.getKey());
                //     stack.offerLast(edge.getKey());
                // }
            }
        }
    }
}
