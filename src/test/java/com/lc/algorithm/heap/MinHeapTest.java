package com.lc.algorithm.heap;

import org.junit.Test;

public class MinHeapTest {
    @Test
    public void testMinHeap() throws Exception {
        MinHeap minHeap = new MinHeap(9);
        minHeap.offer(9);
        minHeap.offer(17);
        minHeap.offer(30);
        minHeap.offer(20);
        minHeap.offer(24);
        minHeap.offer(35);
        minHeap.offer(36);
        minHeap.offer(30);
        minHeap.offer(25);
        while (minHeap.size() != 0) {
            System.out.println(minHeap.poll());
        }
    }
}