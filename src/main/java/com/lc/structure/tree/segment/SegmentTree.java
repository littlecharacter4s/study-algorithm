package com.lc.structure.tree.segment;

/**
 * 线段树
 * 给定一个数组arr，用户希望你实现如下三个方法：
 * 1、void add(int L, int R, int V) :  让数组 arr[L…R] 上每个数都加上 V
 * 2、void update(int L, int R, int V) :  让数组 arr[L…R] 上每个数都变成 V
 * 3、int sum(int L, int R) :让返回 arr[L…R] 这个范围整体的累加和
 * 怎么让这三个方法，时间复杂度都是O(logN)
 *
 * @author gujixian
 * @since 2022/12/26
 */
public class SegmentTree {
    // arr[]为原序列的信息从0开始，但在arr里是从1开始的
    // sum[]模拟线段树维护区间和 -> 线段树的主体数组！！！下标从1开始！！！
    // lazy[]为累加和懒惰标记
    // change[]为更新的值
    // update[]为更新慵懒标记
    private int N;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;
    // l：arr[] 的左边界
    // r：arr[] 的右边界
    // rt：sum[] 的根下标
    private int l;
    private int r;
    private int rt;

    public SegmentTree(int[] origin) {
        l = 1;
        r = origin.length;
        rt = 1;
        N = r + 1;
        arr = new int[N]; // arr[0] 不用 从1开始使用
        for (int i = l; i <= r; i++) {
            arr[i] = origin[i - 1];
        }
        sum = new int[N << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
        lazy = new int[N << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
        change = new int[N << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
        update = new boolean[N << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
    }


    /**
     * 初始化，把 sum[] 数组填好
     * 在 arr[l~r] 范围上，去 build，1~N
     * rt : 这个范围在 sum[] 中的下标
     */
    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }


    /**
     * 将 arr[] 数组 [L...R] 范围上的值都增加 C
     */
    public void add(int L, int R, int C) {
        this.addRecursion(L, R, C, l, r, rt);
    }

    private void addRecursion(int L, int R, int C, int l, int r, int rt) {
        // 任务如果把此时的范围全包了！
        if (L <= l && r <= R) {
            sum[rt] += C * (r - l + 1);
            lazy[rt] += C;
            return;
        }
        // 任务没有把你全包！
        // l  r  mid = (l+r)/2
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        // L~R
        if (L <= mid) {
            addRecursion(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            addRecursion(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }


    /**
     * 将 arr[] 数组 [L...R] 范围上的值设置成 C
     */
    public void update(int L, int R, int C) {
        this.updateRecursion(L, R, C, l, r, rt);
    }

    private void updateRecursion(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            update[rt] = true;
            change[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            return;
        }
        // 当前任务躲不掉，无法懒更新，要往下发
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            updateRecursion(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            updateRecursion(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }


    /**
     * 查询 [L...R] 范围上的累加和
     */
    public long query(int L, int R) {
        return this.queryRecursion(L, R, l, r, rt);
    }

    private long queryRecursion(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (L <= mid) {
            ans += queryRecursion(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            ans += queryRecursion(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }


    /**
     * 上推：父节点的值等于左右子节点值的和
     */
    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }


    /**
     * 下推：
     * 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
     * 分发策略是什么
     * ln表示左子树元素结点个数，rn表示右子树结点个数
     */
    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rn;
            lazy[rt] = 0;
        }
    }

    public static void main(String[] args) {
        int[] origin = new int[]{1,1,1,1,1,2,2,2,2,2,3,3,3,3,3};
        SegmentTree tree = new SegmentTree(origin);
        int l = 1;
        int r = origin.length;
        int rt = 1;
        tree.build(l, r, rt);
        // 打印 1~6 范围上的累加和
        System.out.println(tree.query(1,6));
        // 将 1~6 范围上，每个位置的值都加 3
        tree.add(1, 6, 3);
        System.out.println(tree.query(1,6));
        // 将 1~6 范围上，每个位置的值都设置成 3
        tree.update(1, 6, 3);
        System.out.println(tree.query(1,6));
    }
}
