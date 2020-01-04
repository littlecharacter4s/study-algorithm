package com.lc.algorithm.tree.heap;

import java.util.Arrays;

/**
 * 最小堆，是一种经过排序的完全二叉树，其中任一非终端节点的数据值均不大于其左子节点和右子节点的值
 * 性质1：一棵完全二叉树
 * 性质2：父节点小于等于子节点
 * 实现思路：
 * 1.由于堆是一棵完全二叉树，用数组实现即可（下标从0开始）
 * 2.完全二叉树只有数组下标小于或等于[(数组长度 / 2) - 1]的元素有子节点
 * 3.父节点下标[i]和子节点下标[jl,jr]的关系{
 *     i = (j - 1) >>> 1;
 *     jl = ((i + 1) << 1) - 1;
 *     jr = (i + 1) << 1;
 * }
 * 注：这里以Integer类型的最小堆为例，只实现offer，peek，poll方法
 */
public class MinHeap {
    private int[] elements;
    private int size = 0;

    public MinHeap(int capacity) {
        this.elements = new int[capacity];
    }

    public MinHeap(int[] elements) {
        this.elements = elements;
        this.size = elements.length;
        this.buildHeap();
    }

    public boolean offer(int e) {
        if (size == 0) {
            elements[0] = e;
        } else {
            this.siftUp(size, e);
        }
        size ++;
        return true;
    }

    public Integer peek() {
        return (size == 0) ? null : elements[0];
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }
        int result = elements[0];
        int i = --size;
        int x = elements[i];
        elements = Arrays.copyOf(elements, i);
        //System.out.println(JSON.toJSONString(elements));
        if (i != 0) {
            this.siftDown(0, x);
        }
        return result;
    }

    public int[] getElements() {
        return elements;
    }

    public int size() {
        return size;
    }

    /**
     * 构建堆
     */
    private void buildHeap() {
        // 将有子节点的节点自下而上堆化（使其符合最小堆的特点）
        for (int i = (elements.length >>> 1) - 1; i >= 0; i--) {
            this.heapify(i);
        }
    }

    /**
     * 递归堆化
     * @param i
     */
    private void heapify(int i) {
        // 获取左右结点的数组下标
        int left = ((i + 1) << 1) - 1;
        int right = (i + 1) << 1;

        int min = elements[left] > elements[right] ? right : left;

        if (elements[min] < elements[i]) {
            this.swap(i, min);
            // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
            this.heapify(min);
        }
    }

    private void swap(int i, int j) {
        int tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    /**
     * 由下而上调整节点
     * @param i
     * @param x
     */
    private void siftUp(int i, int x) {
        while (i > 0) {
            int parent = (i - 1) >>> 1;
            int e = elements[parent];
            if (x >= e) {
                break;
            }
            elements[i] = e;
            i = parent;
        }
        elements[i] = x;
    }

    // 自上而下调整节点
    private void siftDown(int i, int x) {
        // 第一个无子节点的节点
        int half = size >>> 1;
        while (i < half) {
            int left = ((i + 1) << 1) - 1;
            int right = (i + 1) << 1;
            // 最后一个有子节点的节点可能没有右节点，这里处理一下防止越界
            right = right < size ? right : left;
            int min = elements[left] > elements[right] ? right : left;
            if (x <= elements[min]) {
                break;
            }
            elements[i] = elements[min];
            i = min;
        }
        elements[i] = x;
    }
}
