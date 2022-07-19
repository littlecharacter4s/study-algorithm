package com.lc.algorithm.structure.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedStack<E> {
    private LinkedList<E> list;

    public LinkedStack() {
        list = new LinkedList<>();
    }

    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        return list.removeLast();
    }

    public void push(E element) {
        list.add(element);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return list.getLast();
    }

    public List<E> get() {
        return new LinkedList<>(list);
    }
}
