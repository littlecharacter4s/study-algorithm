package com.lc.structure.zother;

import lombok.*;

import java.util.*;

/**
 * @author gujixian
 * @since 2022/11/30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnionFindSet<V> {
    // 值和 node 一比一映射
    public Map<V, Node<V>> nodeMap = new HashMap<>();
    // 每个 node 和 head 的映射
    public Map<Node<V>, Node<V>> headMap = new HashMap<>();
    // 每个 head 代表的集合大小
    public Map<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionFindSet(List<V> valueList) {
        if (Objects.isNull(valueList) || valueList.isEmpty()) {
            return;
        }
        for (V value : valueList) {
            if (Objects.isNull(value)) {
                continue;
            }
            Node<V> node = new Node<>(value);
            nodeMap.put(value, node);
            headMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<V> add(V value) {
        if (Objects.isNull(value)) {
            return null;
        }
        Node<V> node = new Node<>(value);
        nodeMap.put(value, node);
        headMap.put(node, node);
        sizeMap.put(node, 1);
        return node;
    }

    public Node<V> remove(V value) {
        Node<V> node = nodeMap.get(value);
        if (Objects.isNull(node)) {
            return null;
        }
        Node<V> head = headMap.get(node);
        headMap.remove(node);
        sizeMap.put(head, sizeMap.get(head) - 1);
        return node;
    }

    public boolean decide(V v1, V v2) {
        if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
            return false;
        }
        return this.findHead(nodeMap.get(v1)) == this.findHead(nodeMap.get(v2));
    }

    public void union(V v1, V v2) {
        if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
            return;
        }
        Node<V> v1Head = this.findHead(nodeMap.get(v1));
        Node<V> v2Head = this.findHead(nodeMap.get(v2));
        if (Objects.isNull(v1Head) || Objects.isNull(v2Head)) {
            return;
        }
        if (v1Head != v2Head) {
            Integer v1HeadSize = sizeMap.get(v1Head);
            Integer v2HeadSize = sizeMap.get(v2Head);
            Node<V> big = v1HeadSize > v2HeadSize ? v1Head : v2Head;
            Node<V> small = big == v1Head ? v2Head : v1Head;
            headMap.put(small, big);
            sizeMap.put(big, v1HeadSize + v2HeadSize);
            sizeMap.remove(small);
        }
    }

    public int getSetSize() {
        return sizeMap.size();
    }

    private Node<V> findHead(Node<V> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        Queue<Node<V>> queue = new LinkedList<>();
        while (node != headMap.get(node)) {
            queue.offer(node);
            node = headMap.get(node);
        }
        // 压缩
        while (!queue.isEmpty()) {
            headMap.put(queue.poll(), node);
        }
        return node;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node<V> {
        public V value;
    }
}
