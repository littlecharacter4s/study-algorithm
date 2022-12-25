package com.lc.structure.linear.queue;

/**
 * 环形队列：性能差，比另一个，常数项时间复杂度高
 * @param <T>
 */
public class CircleQueue<T> {
    private final int capacity;
    private int getIndex = 0;
    private int putIndex = 0;
    private Object[] queue;

    public CircleQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }
        queue[putIndex++] = t;
        putIndex = putIndex % capacity;
        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }
        Object obj = queue[getIndex++];
        getIndex = getIndex % capacity;
        return obj == null ? null : (T) obj;
    }

    public Object[] getAll() {
        return queue;
    }

    private boolean isFull() {
        return (putIndex + 1) % capacity == getIndex;
    }

    private boolean isEmpty() {
        return getIndex == putIndex;
    }
}
