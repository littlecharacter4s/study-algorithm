package com.lc.algorithm.structure.stack;

import java.util.ArrayList;
import java.util.List;

public class ArrayStack<E> {
    private ArrayList<E> list;

    public ArrayStack() {
        list = new ArrayList<>();
    }

    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        return list.remove(list.size() - 1);
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
        return list.get(list.size() - 1);
    }

    public List<E> get() {
        return new ArrayList<>(list);
    }
}
