package com.lc.structure.linear.queue;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.TimeUnit;

/**
 * 环形队列：性能好，比另一个，常数项时间复杂度低
 * @param <T>
 */
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


    public static void main(String[] args) {
        CycleQueue<Integer> cycleQueue = new CycleQueue<>(4);
        new Thread(() -> {
            int i = 1;
            while (true) {
                Integer result = cycleQueue.put(i);
                System.out.println("provider[" + Thread.currentThread().getName() + "]:" + JSON.toJSONString(cycleQueue.getAll()));
                try {
                    //TimeUnit.MILLISECONDS.sleep(Math.round(5000 * Math.random()));
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = result == null ? i : i + 1;
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Integer i = cycleQueue.get();
                System.out.println("consumer[" + Thread.currentThread().getName() + "]:" + i);
                try {
                    //TimeUnit.MILLISECONDS.sleep(Math.round(10000 * Math.random()));
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
