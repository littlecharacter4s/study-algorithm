package com.lc.algorithm.queue;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class QueueTest {
    @Test
    public void testCycleQueue() {
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

    @Test
    public void testCircleQueue() {
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