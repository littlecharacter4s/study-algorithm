package com.lc.algorithm.queue;

/**
 * 尾进头出
 * @param <T>
 */
public class CycleQueue<T> {
    private final int capacity;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] queue;

    public CycleQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }

        queue[tail ++] = t;
        tail = tail == capacity ? tail % capacity : tail;

        size ++;

        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }

        Object obj = queue[head];
        queue[head ++] = null;
        head = head == capacity ? head % capacity : head;

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
