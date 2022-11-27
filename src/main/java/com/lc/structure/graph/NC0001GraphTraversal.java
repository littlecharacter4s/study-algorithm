package com.lc.structure.graph;

import com.google.common.collect.Lists;
import javafx.util.Pair;

import java.util.*;

/**
 * @author gujixian
 * @since 2022/11/28
 */
public class NC0001GraphTraversal {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // 无向图
        // graph.graph.put(0, new HashSet<>(Arrays.asList(new Pair<>(1, 1), new Pair<>(3, 1), new Pair<>(4, 1))));
        // graph.graph.put(1, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(2, 1), new Pair<>(4, 1))));
        // graph.graph.put(2, new HashSet<>(Arrays.asList(new Pair<>(1, 1), new Pair<>(3, 1))));
        // graph.graph.put(3, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(2, 1), new Pair<>(4, 1))));
        // graph.graph.put(4, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(3, 1))));
        // 有向图
        graph.graph.put(0, new HashSet<>(Arrays.asList(new Pair<>(1, 1), new Pair<>(3, 1), new Pair<>(4, 1))));
        graph.graph.put(1, new HashSet<>(Arrays.asList(new Pair<>(2, 1), new Pair<>(4, 1))));
        graph.graph.put(2, new HashSet<>(Arrays.asList(new Pair<>(5, 1), new Pair<>(6, 1))));
        graph.graph.put(3, new HashSet<>(Arrays.asList(new Pair<>(2, 1), new Pair<>(4, 1))));

        NC0001GraphTraversal graphTraversal = new NC0001GraphTraversal();
        System.out.println("广度优先遍历");
        graphTraversal.bfs(graph);

        System.out.println("深度优先遍历V1(基于递归和栈的深度优先遍历虽然结果不一致，但都是深度优先遍历)");
        Set<Integer> visitedNode = new HashSet<>();
        visitedNode.add(0);
        graphTraversal.dfsV1(graph, visitedNode, 0);

        System.out.println("深度优先遍历V2(基于递归和栈的深度优先遍历虽然结果不一致，但都是深度优先遍历)");
        graphTraversal.dfsV2(graph);
    }

    private void dfsV1(Graph graph, Set<Integer> visitedNode, int node) {
        System.out.println(node);
        Set<Pair<Integer, Integer>> edges = graph.graph.get(node);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        for (Pair<Integer, Integer> edge : edges) {
            if (visitedNode.contains(edge.getKey())) {
                continue;
            }
            visitedNode.add(edge.getKey());
            dfsV1(graph, visitedNode, edge.getKey());
        }
    }

    private void dfsV2(Graph graph) {
        Set<Integer> visitedNode = new HashSet<>();
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(0);
        visitedNode.add(0);
        while (!stack.isEmpty()) {
            int node = stack.pollLast();
            System.out.println(node);
            Set<Pair<Integer, Integer>> edges = graph.graph.get(node);
            if (edges == null || edges.isEmpty()) {
                continue;
            }
            for (Pair<Integer, Integer> edge : graph.graph.get(node)) {
                if (visitedNode.contains(edge.getKey())) {
                    continue;
                }
                visitedNode.add(edge.getKey());
                stack.offerLast(edge.getKey());
            }
        }
    }

    private void bfs(Graph graph) {
        Set<Integer> visitedNode = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visitedNode.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.println(node);
            Set<Pair<Integer, Integer>> edges = graph.graph.get(node);
            if (edges == null || edges.isEmpty()) {
                continue;
            }
            for (Pair<Integer, Integer> edge : graph.graph.get(node)) {
                if (visitedNode.contains(edge.getKey())) {
                    continue;
                }
                visitedNode.add(edge.getKey());
                queue.offer(edge.getKey());
            }
        }
    }
}
