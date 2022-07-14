package com.lc.structure.queue;

/**
 * 尾进头出
 * @param <T>
 */
public class CircleQueue<T> {
    private final int capacity;
    private int head = 0;
    private int tail = 0;
    private Object[] queue;

    public CircleQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }

        queue[tail] = t;
        tail = (tail + 1) % capacity;

        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }

        Object obj = queue[head];
        head = (head + 1) % capacity;

        return obj == null ? null : (T) obj;
    }

    public Object[] getAll() {
        return queue;
    }

    private boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    private boolean isEmpty() {
        return head == tail;
    }
}
