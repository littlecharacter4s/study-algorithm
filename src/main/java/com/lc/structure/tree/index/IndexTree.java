package com.lc.structure.tree.index;

import com.alibaba.fastjson.JSON;

/**
 * IndexTree（支持变化的前缀和数组，线段树的一个小弟）：利用下标和二进制的规律组织成的树
 *
 * @author gujixian
 * @since 2022/12/26
 */
public class IndexTree {
    /**
     * IndexTree 的主体数组！！！下标从1开始！！！
     *
     * 规律:
     * tree[index] 表示 nums[] 数组[(index ^ (index & -index) + 1)...index] 范围上的累加和
     * index ^ (index & -index) + 1：表示 index 抹掉最后一个 1 再加 1
     */
    private int[] tree;
    private int N;
    private int[] nums;

    // 0位置弃而不用！
    public IndexTree(int size) {
        N = size;
        tree = new int[N + 1];
    }

    // 0位置弃而不用！
    public IndexTree(int[] origin) {
        N = origin.length;
        tree = new int[N + 1];
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            add(i, origin[i - 1]);
        }
    }

    /**
     * 1~index 累加和是多少？
     * index & -index : 提取出index最右侧的1出来
     * index :           0011001000
     * index & -index :  0000001000
     */
    public int sum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }


    /**
     * 给 index 位置加 v
     * index & -index : 提取出index最右侧的1出来
     * index :           0011001000
     * index & -index :  0000001000
     */
    public void add(int index, int v) {
        nums[index] += v;
        while (index <= N) {
            tree[index] += v;
            index += index & -index;
        }
        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(tree));
        System.out.println("---------------------");
    }

    public static void main(String[] args) {
        int[] origin = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        IndexTree tree = new IndexTree(origin);
        System.out.println(tree.sum(3));
        tree.add(2, 2);
        System.out.println(tree.sum(3));
    }
}
