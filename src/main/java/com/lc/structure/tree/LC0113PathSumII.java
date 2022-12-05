package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class LC0113PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l1001 = new TreeNode(4);
        TreeNode l1002 = new TreeNode(8);
        root.left = l1001;
        root.right = l1002;
        TreeNode l2001 = new TreeNode(11);
        TreeNode l2002 = new TreeNode(13);
        TreeNode l2003 = new TreeNode(4);
        l1001.left = l2001;
        l1002.left = l2002;
        l1002.right = l2003;
        TreeNode l3001 = new TreeNode(7);
        TreeNode l3002 = new TreeNode(2);
        TreeNode l3003 = new TreeNode(5);
        TreeNode l3004 = new TreeNode(1);
        l2001.left = l3001;
        l2001.right = l3002;
        l2003.left = l3003;
        l2003.right = l3004;
        List<List<Integer>> result = new LC0113PathSumII().pathSum(root, 22);
        System.out.println(JSON.toJSONString(result));

        // 哈哈
        System.out.println("哈哈");

    }

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

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
