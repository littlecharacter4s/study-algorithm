package com.lc.structure.linear.queue;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.TimeUnit;

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


    public static void main(String[] args) {
        CircleQueue<Integer> circleQueue = new CircleQueue(4);
        new Thread(() -> {
            int i = 1;
            while (true) {
                Integer result = circleQueue.put(i);
                System.out.println("provider[" + Thread.currentThread().getName() + "]:" + JSON.toJSONString(circleQueue.getAll()));
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = result == null ? i : i + 1;
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Integer i = circleQueue.get();
                System.out.println("consumer[" + Thread.currentThread().getName() + "]:" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
