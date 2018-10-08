package com.lc.algorithm.queue;

public class CircleQueue<T> {
    private final int LEN;
    private int head = 0;
    private int tail = 0;
    private Object[] queue;

    public CircleQueue(int length) {
        this.LEN = length;
        queue = new Object[LEN];
    }

    public T put(T t) {
        if (this.isFull()) {
            return null;
        }

        queue[head ++] = t;
        head = head >= LEN ? head % LEN : head;

        return t;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (this.isEmpty()) {
            return null;
        }

        Object obj = queue[tail];
        queue[tail ++] = null;
        tail = tail >= LEN ? tail % LEN : tail;

        return obj == null ? null : (T) obj;
    }

    public Object[] getAll() {
        return queue;
    }

    private boolean isFull() {
        return head == tail && queue[head] != null;
    }

    private boolean isEmpty() {
        return head == tail && queue[head] == null;
    }
}
