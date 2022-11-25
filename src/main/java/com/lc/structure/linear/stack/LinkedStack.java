package com.lc.structure.linear.stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedStack<E> {
    private Queue<E> stack;

    public LinkedStack() {
        stack = new LinkedList<>();
    }
    public void push(E element) {
        stack.add(element);
    }

    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.poll();
    }

    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public List<E> getAll() {
        return new LinkedList<>(stack);
    }

    private boolean isEmpty() {
        return stack.isEmpty();
    }
}
