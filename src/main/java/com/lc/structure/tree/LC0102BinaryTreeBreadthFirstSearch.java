package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class LC0102BinaryTreeBreadthFirstSearch {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        List<List<Integer>> result = new LC0102BinaryTreeBreadthFirstSearch().levelOrder(root);
        System.out.println(JSON.toJSONString(result));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            List<Integer> subResult = new ArrayList<>(levelLength);
            for (int i = 0; i < levelLength; i++) {
                TreeNode node = queue.poll();
                subResult.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(subResult);
        }
        return result;
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
