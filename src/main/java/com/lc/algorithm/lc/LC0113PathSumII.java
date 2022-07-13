package com.lc.algorithm.lc;

import java.util.ArrayList;
import java.util.List;

public class LC0113PathSumII {
    private List<Integer> result = new ArrayList<>();
    private List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return results;
        }
        this.search(root, 0, sum);
        return results;
    }

    private void search(TreeNode node, int sum, int target) {
        result.add(node.val);
        if (node.left == null && node.right == null) {
            if (sum + node.val == target) {
                results.add(new ArrayList(result));
            }
            result.remove(result.size() - 1);
            return;
        }
        if (node.left != null) {
            search(node.left, sum + node.val, target);
        }
        if (node.right != null) {
            search(node.right, sum + node.val, target);
        }
        result.remove(result.size() - 1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
