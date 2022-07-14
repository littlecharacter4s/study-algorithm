package com.lc.structure.stack;

public class LinkedStack {
    private int size = 0;
    private Node head;
    private Node max = head;

    public void push(int element) {
        Node node = new Node(element);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        this.afterPush(node);
        size++;
    }

    private void afterPush(Node node) {
        if (max == null || max.element < node.element) {
            max = node;
        }
    }

    public Integer pop() {
        if (head == null) {
            return null;
        }
        int element = head.element;
        this.afterPop(head);
        head = head.next;
        size--;
        return element;
    }

    private void afterPop(Node node) {
        if (max != node) {
            return;
        }
        Node current = max = max.next;
        while (current != null) {
            if (max.element < current.element) {
                max = current;
            }
            current = current.next;
        }
    }

    public Integer max() {
        if (max == null) {
            return null;
        }
        return max.element;
    }

    private static class Node {
        int element;
        Node next;

        Node(Integer element) {
            this.element = element;
        }
    }
}
