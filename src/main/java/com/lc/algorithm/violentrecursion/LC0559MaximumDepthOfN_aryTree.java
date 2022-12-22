package com.lc.algorithm.violentrecursion;

import java.util.Arrays;
import java.util.List;

/**
 * 题目：N 叉树的最大深度
 * 描述：
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 * 分析：
 * 链接：https://leetcode.cn/problems/maximum-depth-of-n-ary-tree/
 */
public class LC0559MaximumDepthOfN_aryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.children = Arrays.asList(new Node(2), new Node(3), new Node(4));
        root.children.get(0).children = Arrays.asList(new Node(5), new Node(6));
        root.children.get(0).children.get(0).children = Arrays.asList(new Node(7), new Node(8));
        System.out.println(new LC0559MaximumDepthOfN_aryTree().maxDepth(root));
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.children == null || root.children.isEmpty()) {
            return 1;
        }
        int max = 0;
        for (int i = 0; i < root.children.size(); i++) {
            // 遍历所有子树，找出深度最深的子树的深度
            max = Math.max(max, maxDepth(root.children.get(i)));
        }
        // 循环遍历一层子树，层数加1
        return 1 + max;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
