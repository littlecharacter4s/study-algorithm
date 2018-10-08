package com.lc.algorithm.queue;

public class CycleQueue<T> {
    private final int LEN;
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] queue;

    public CycleQueue(int length) {
        this.LEN = length;
        queue = new Object[LEN];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }

        queue[head ++] = t;
        head = head == LEN ? head % LEN : head;

        size ++;

        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }

        Object obj = queue[tail];
        queue[tail ++] = null;
        tail = tail == LEN ? tail % LEN : tail;

        size --;

        return obj == null ? null : (T) obj;
    }

    public Object[] getAll() {
        return queue;
    }

    private boolean isFull() {
        return size == LEN;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
