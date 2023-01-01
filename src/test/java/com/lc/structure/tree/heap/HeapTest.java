package com.lc.structure.tree.heap;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class HeapTest {
    @Test
    public void testMinHeap() throws Exception {
        Heap heap = new Heap(9);
        heap.offer(9);
        heap.offer(17);
        heap.offer(30);
        heap.offer(20);
        heap.offer(24);
        heap.offer(35);
        heap.offer(36);
        heap.offer(25);
        heap.offer(30);
        int[] elements = heap.getElements();
        System.out.println(JSON.toJSONString(elements));
        // while (minHeap.size() != 0) {
        //     System.out.println(minHeap.poll());
        // }
        heap.poll();
        elements = heap.getElements();
        System.out.println(JSON.toJSONString(elements));
    }
}