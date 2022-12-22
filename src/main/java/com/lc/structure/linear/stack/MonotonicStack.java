package com.lc.structure.linear.stack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 单调栈
 *
 * @author gujixian
 * @since 2022/12/22
 */
public class MonotonicStack<V> {
    private final Deque<Node<V>> stack = new LinkedList<>();
    private final Comparator<V> comparator;
    private final int capacity;
    private int size;

    public MonotonicStack(Comparator<V> comparator, int capacity) {
        this.comparator = comparator;
        this.capacity = capacity;
    }

    public List<Node<V>> push(Node<V> node) {
        List<Node<V>> featureList = new LinkedList<>();
        if (Objects.isNull(node)) {
            return featureList;
        }
        if (stack.isEmpty()) {
            stack.offerLast(node);
        } else {
            while (!stack.isEmpty()) {
                Node<V> top = stack.peekLast();
                if (comparator.compare(top.value, node.value) > 0) {
                    // 小于栈顶
                    stack.pollLast();
                    int left = stack.peekLast() == null ? -1 : stack.peekLast().indexQueue.peekLast();
                    for (Integer index : top.indexQueue) {
                        Node<V> feature = new Node<>(index, top.value);
                        feature.right = node.index;
                        feature.left = left;
                        featureList.add(feature);
                    }
                    if (stack.peekLast() == null) {
                        stack.offerLast(node);
                        break;
                    }
                } else if (comparator.compare(top.value, node.value) < 0) {
                    // 大于栈顶
                    stack.offerLast(node);
                    break;
                } else {
                    // 等于栈顶
                    top.indexQueue.offerLast(node.index);
                    break;
                }
            }
        }
        size++;
        // 数组遍历完了，对栈中剩余的每个元素求特征值
        if (size == capacity) {
            while (!stack.isEmpty()) {
                Node<V> top = stack.pollLast();
                int left = stack.peekLast() == null ? -1 : stack.peekLast().indexQueue.peekLast();
                for (Integer index : top.indexQueue) {
                    Node<V> feature = new Node<>(index, top.value);
                    feature.right = capacity;
                    feature.left = left;
                    featureList.add(feature);
                }
            }
        }
        return featureList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node<V> {
        private int index;
        private V value;
        // 当前节点的左右边界（左开右开）
        private int left;
        private int right;
        // 处理值相等的情况
        private Deque<Integer> indexQueue = new LinkedList<>();

        public Node(int index, V value) {
            this.index = index;
            this.value = value;
            this.indexQueue.offerLast(index);
        }
    }
}
