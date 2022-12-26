package com.lc.structure.tree.index;

/**
 * IndexTree（支持变化的前缀和数组，线段树的一个小弟）：利用下标和二进制的规律组织成的树
 *
 * @author gujixian
 * @since 2022/12/26
 */
public class IndexTree2D {
    // 下标从1开始！！！
    private int[][] tree;
    // 下标从1开始！！！
    private int[][] nums;
    private int N;
    private int M;

    // 0位置弃而不用！
    public IndexTree2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        N = matrix.length;
        M = matrix[0].length;
        tree = new int[N + 1][M + 1];
        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.update(i, j, matrix[i][j]);
            }
        }
    }


    /**
     * [1,row][1,col] 区域累加和是多少？
     */
    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }


    /**
     * 将 nums[row][col] 位置更新为 val
     */
    public void update(int row, int col, int val) {
        if (N == 0 || M == 0) {
            return;
        }
        int add = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= N; i += i & (-i)) {
            for (int j = col + 1; j <= M; j += j & (-j)) {
                tree[i][j] += add;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1},{2,2,2},{3,3,3}};
        IndexTree2D tree = new IndexTree2D(matrix);
        System.out.println(tree.sum(2, 2));
    }
}
