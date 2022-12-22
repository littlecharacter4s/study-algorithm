package com.lc.structure.zother;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口
 * @author gujixian
 * @since 2022/12/22
 */
public class SlidingWindow<E> {
    private final Deque<Node<E>> window = new LinkedList<>();
    private final Comparator<E> comparator;
    private final int capacity;
    private int head;
    private int tail;

    public SlidingWindow(Comparator<E> comparator, int capacity) {
        this.comparator = comparator;
        this.capacity = capacity;
    }

    /**
     * slide 模拟窗口滑动，当窗口形成后，每次滑动都会产生特征值
     * @param node
     * @return
     */
    public Node<E> slide(Node<E> node) {
        Node<E> feature = null;
        while (!window.isEmpty()) {
            Node<E> x = window.peekLast();
            if (comparator.compare(x.getElement(), node.getElement()) > 0) {
                window.offerLast(node);
                break;
            } else {
                window.pollLast();
            }
        }
        if (window.isEmpty()) {
            window.offerLast(node);
        }
        // 窗口形成，之后总是满足
        if (tail - head + 1 == capacity) {
            feature = window.peekFirst();
            assert feature != null;
            if (feature.index == head) {
                window.pollFirst();
            }
            head++;
        }
        tail++;
        return feature;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node<E> {
        private int index;
        private E element;
    }
}
