package com.lc.algorithm;

import java.util.List;

public class LC0559MaximumDepthOfN_aryTree {
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

    static class Node {
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
