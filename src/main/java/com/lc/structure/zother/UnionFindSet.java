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
    // 工程：每个 node 和 head 的映射
    public Map<Node<V>, Node<V>> headMap = new HashMap<>();
    // 工程：每个 head 代表的集合大小
    public Map<Node<V>, Integer> sizeMap = new HashMap<>();

    // 面试：每个 node 和 head 的映射
    // public int[] headArray;
    // 面试：每个 head 代表的集合大小
    // public int[] headSize;

    public UnionFindSet(Collection<Node<V>> nodeList) {
        if (Objects.isNull(nodeList) || nodeList.isEmpty()) {
            return;
        }
        for (Node<V> node : nodeList) {
            if (Objects.isNull(node)) {
                continue;
            }
            headMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<V> add(Node<V> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        headMap.put(node, node);
        sizeMap.put(node, 1);
        return node;
    }

    public Node<V> remove(Node<V> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        Node<V> head = headMap.get(node);
        if (Objects.isNull(head)) {
            return null;
        }
        headMap.remove(node);
        sizeMap.put(head, sizeMap.get(head) - 1);
        return node;
    }

    public boolean decide(Node<V> node1, Node<V> node2) {
        Node<V> head1 = this.findHead(node1);
        Node<V> head2 = this.findHead(node2);
        return !Objects.isNull(head1) && !Objects.isNull(head2) && head1 == head2;
    }

    public void union(Node<V> node1, Node<V> node2) {
        Node<V> head1 = this.findHead(node1);
        Node<V> head2 = this.findHead(node2);
        if (Objects.isNull(head1) || Objects.isNull(head2)) {
            return;
        }
        if (head1 != head2) {
            Integer head1Size = sizeMap.get(head1);
            Integer head2Size = sizeMap.get(head2);
            Node<V> big = head1Size > head2Size ? head1 : head2;
            Node<V> small = big == head1 ? head2 : head1;
            headMap.put(small, big);
            sizeMap.put(big, head1Size + head2Size);
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
