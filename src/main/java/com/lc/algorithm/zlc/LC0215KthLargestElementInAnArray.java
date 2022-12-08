package com.lc.algorithm.zlc;

public class LC0215KthLargestElementInAnArray {
    public int findKthLargestElement(int[] elements, int k) {
        if (k > elements.length || k < 1) {
            return Integer.MIN_VALUE;
        }
        int[] minHeap = new int[k];
        for (int i = 0; i < elements.length; i++) {
            if (i < k) {
                this.buildMinHeap(minHeap, i, elements[i]);
            } else if (elements[i] > minHeap[0]) {
                this.adjustMinHeap(minHeap, k, elements[i]);
            }
        }
        return minHeap[0];
    }

    /**
     * 自下而上插入并调整节点
     * @param minHeap
     * @param i
     * @param e
     */
    private void buildMinHeap(int[] minHeap, int i, int e) {
        if (i == 0) {
            minHeap[0] = e;
        } else {
            while (i > 0) {
                int parent = (i - 1) >>> 1;
                int x = minHeap[parent];
                if (e >= x) {
                    break;
                }
                minHeap[i] = x;
                i = parent;
            }
            minHeap[i] = e;
        }
    }

    /**
     * 设置根节点为新值并自上而下调整节点
     * @param minHeap
     * @param k
     * @param e
     */
    private void adjustMinHeap(int[] minHeap, int k, int e) {
        // 第一个无子节点的节点
        int half = k >>> 1;
        int i = 0;
        while (i < half) {
            int left = ((i + 1) << 1) - 1;
            int right = (i + 1) << 1;
            // 最后一个有子节点的节点可能没有右节点，这里处理一下防止越界
            right  = right >= k ? left : right;
            int min = minHeap[left] > minHeap[right] ? right : left;
            if (e <= minHeap[min]) {
                break;
            }
            minHeap[i] = minHeap[min];
            i = min;
        }
        minHeap[i] = e;
    }
}
