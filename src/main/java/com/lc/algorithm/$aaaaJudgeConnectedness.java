package com.lc.algorithm;

/**
 * 问题：判断点是否连通（任意两个点直接或间接连接）
 * 描述：有x个点【x ∈ (0,n]，其中n∈Z】和m条边【[[x₁,x₂],[x₃,x₂]...[xi,xj]]】，判断点是否连通
 * 思路：用并查集来实现（https://baike.baidu.com/item/%E5%B9%B6%E6%9F%A5%E9%9B%86/9388442?fr=aladdin）
 * 分析：O(m) + O(n) + O(所有连通子图的深度和) = O(n)
 */
public class $aaaaJudgeConnectedness {
    /**
     * @param edges {m条边}
     * @param limit {n,x的大小限制}
     * @return
     */
    public boolean isConnected(int[][] edges, int limit) {
        int[] points = new int[limit + 1];
        for (int[] edge : edges) {
            int p1 = edge[0];
            int p2 = edge[1];
            // 1.验证输入数据的有效性
            if (edge.length != 2
                    || p1 < 1
                    || p1 > limit
                    || p2 < 1
                    || p2 > limit) {
                System.out.println("数据输入有误!");
                return false;
            }
            // 2.归并连通集（连通子图）
            this.union(points, p1, p2);
        }
        System.out.println("归并结果：");
        for (int i = 0; i < points.length; i++) {
            System.out.println("points[" + i + "]=" + points[i]);
        }
        // 3.判断是否连通
        int root = 0;
        for (int i = 1; i < points.length; i++) {
            if (root == 0 && points[i] != 0) {
                root = this.find(points, i);
            }
            if (points[i] != 0 && root != this.find(points, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 归并连通集（连通子图）
     * @param points
     * @param p1
     * @param p2
     */
    private void union(int[] points, int p1, int p2) {
        points[p1] = points[p1] == 0 ? p1 : points[p1];
        points[p2] = points[p2] == 0 ? p2 : points[p2];
        // 寻找各连通集（连通子图）的根节点
        int p1Root = this.find(points, p1);
        int p2Root = this.find(points, p2);
        if (p1Root != p2Root) {
            // 用大小代表优先级，编号小的是领导
            points[p1Root > p2Root ? p1Root : p2Root] = p1Root < p2Root ? p1Root : p2Root;
        }
    }

    /**
     * 查找连通集（连通子图）的根节点
     * @param points
     * @param p
     * @return
     */
    private int find(int[] points, int p) {
        // 找根节点（为了压缩路径，这里用递归实现更好）
        int root = points[p] == p ? p : find(points, points[p]);
        /*压缩路径
         ************************
         *       1    *    1    *
         *    2     3 * 2  3  x *
         * x          *         *
         **********************************
         *       1       *      1         *
         *    2     3    * 2    x    3    *
         * x     6     5 *        6     5 *
         **********************************
         */
        if (points[p] != root) {
            points[p] = root;
        }
        return root;
    }
}
