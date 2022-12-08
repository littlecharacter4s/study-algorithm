package com.lc.algorithm.zlc;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：图结构，用并查集来实现（https://baike.baidu.com/item/%E5%B9%B6%E6%9F%A5%E9%9B%86/9388442?fr=aladdin），构造有向连通子图
 * 分析：O(n) < 复杂度 < O(n*(n-1))
 */
public class LC0399EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Integer> paramTable = new HashMap<>();
        int length = 0;
        // 1.为方程中涉及的所有参数编号（舍弃重复的），并得出有向图的空间大小
        for (String[] equation : equations) {
            if (!paramTable.containsKey(equation[0])) {
                paramTable.put(equation[0], length++);
            }
            if (!paramTable.containsKey(equation[1])) {
                paramTable.put(equation[1], length++);
            }
        }
        // 2.初始化有向图，父节点都指向自己
        Node[] nodes = new Node[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new Node(i);
        }
        // 3.归并连通集（连通子图）
        for (int i = 0; i < equations.length; i++) {
            int p1 = paramTable.get(equations[i][0]);
            int p2 = paramTable.get(equations[i][1]);
            int p1Root = find(nodes, p1);
            int p2Root = find(nodes, p2);
            // 被除数（的根节点）作为父节点
            nodes[p2Root].parent = p1Root;
            // 父节点和子节点的比值（parent/child）存在子节点value中
            nodes[p2Root].value = nodes[p1].value * values[i] / nodes[p2].value;
        }
        System.out.println("归并结果：");
        for (int i = 0; i < nodes.length; i++) {
            System.out.println("nodes[" + i + "]={parent=" + nodes[i].parent + ",value=" + nodes[i].value + "}");
        }
        // 4.计算结果
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer p1 = paramTable.get(queries[i][0]);
            Integer p2 = paramTable.get(queries[i][1]);
            if (p1 == null || p2 == null) {
                result[i] = -1d;
            } else {
                int p1Root = find(nodes, p1);
                int p2Root = find(nodes, p2);
                if (p1Root != p2Root) {
                    result[i] = -1d;
                } else {
                    result[i] = nodes[p2].value / nodes[p1].value;
                }
            }
        }
        return result;
    }

    /**
     * 归并连通集（连通子图）
     * @param nodes
     * @param node
     * @return
     */
    private int find(Node[] nodes, int node) {
        int root = node;
        while (nodes[root].parent != root) {
            root = nodes[root].parent;
            // 当前节点到根节点经过的所有节点值的乘积，即为根节点和当前节点的比值（root/node），将其存入当前节点
            nodes[node].value *= nodes[root].value;
        }
        // 当前节点的父节点指向其根节点
        nodes[node].parent = root;
        return root;
    }

    private class Node {
        int parent;
        double value;
        Node(int index) {
            this.parent = index;
            this.value = 1d;
        }
    }
}
