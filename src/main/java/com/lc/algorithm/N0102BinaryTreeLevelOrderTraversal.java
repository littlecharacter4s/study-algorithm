package com.lc.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class N0102BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        ConcurrentLinkedQueue<TreeNode> queue = new ConcurrentLinkedQueue<>();
        if (root == null) {
            return result;
        }
        result.add(Collections.singletonList(root.val));
        if (root.left == null && root.right == null) {
            return result;
        }
        if (root.left != null) {
            queue.offer(root.left);
        }
        if (root.right != null) {
            queue.offer(root.right);
        }
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        boolean flag = false;
        while(queue.peek() != null) {
            TreeNode node = queue.poll();
            if (node == root) {
                if (!list.isEmpty()) {
                    result.add(list);
                    list = new ArrayList<>();
                }
                if (flag) {
                    queue.offer(root);
                    flag = false;
                }
                continue;
            }
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
                flag = true;
            }
            if (node.right != null) {
                queue.offer(node.right);
                flag = true;
            }
        }
        return result;
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
