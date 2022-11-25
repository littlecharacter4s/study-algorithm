package com.lc.structure.linear.queue;

public class CycleQueue<T> {
    private final int capacity;
    private int size = 0;
    private int getIndex = 0;
    private int putIndex = 0;
    private Object[] queue;

    public CycleQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }
        queue[putIndex ++] = t;
        putIndex = putIndex % capacity;
        size ++;
        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }
        Object obj = queue[getIndex];
        queue[getIndex ++] = null;
        getIndex = getIndex % capacity;
        size --;
        return obj == null ? null : (T) obj;
    }

    public Object[] getAll() {
        return queue;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
