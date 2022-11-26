package com.lc.structure.graph;

import lombok.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author gujixian
 * @since 2022/11/25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Graph<E> {
    // 点编号 : 点结构
    public HashMap<Integer, Node<E>> nodeMap = new HashMap<>();
    public HashSet<Edge<E>> edgeSet = new HashSet<>();

    public static Graph<Integer> buildGraph(Integer[][] matrix) {
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];

            graph.nodeMap.putIfAbsent(from, new Node<>(from));
            graph.nodeMap.putIfAbsent(to, new Node<>(to));

            Node<Integer> fromNode = graph.nodeMap.get(from);
            Node<Integer> toNode = graph.nodeMap.get(to);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;

            Edge<Integer> edge = new Edge<>(weight, fromNode, toNode);
            fromNode.edges.add(edge);
            // toNode.edges.add(edge); // 因为是有向图，这条边只属于 fromNode
            graph.edgeSet.add(edge);
        }
        return graph;
    }

    public static <T> Graph<T> buildGraphV2(T[][] matrix) {
        Graph<T> graph = new Graph<>();
        for (int i = 0; i < matrix.length; i++) {
            T from = matrix[i][0];
            T to = matrix[i][1];
            T weight = matrix[i][2];

            graph.nodeMap.putIfAbsent((Integer) from, new Node<T>((T) from));
            graph.nodeMap.putIfAbsent((Integer) to, new Node<T>((T) to));

            Node<T> fromNode = graph.nodeMap.get(from);
            Node<T> toNode = graph.nodeMap.get(to);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;

            Edge<T> edge = new Edge<>((Integer) weight, fromNode, toNode);
            fromNode.edges.add(edge);
            // toNode.edges.add(edge); // 因为是有向图，这条边只属于 fromNode
            graph.edgeSet.add(edge);
        }
        return graph;
    }
}
