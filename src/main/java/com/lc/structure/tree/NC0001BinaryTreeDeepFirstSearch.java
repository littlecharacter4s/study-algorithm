package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class NC0001BinaryTreeDeepFirstSearch {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        new NC0001BinaryTreeDeepFirstSearch().deepOrder(root);
    }

    public void deepOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print("先序遍历：");
        this.headFirstDFS(root);
        System.out.print("\n后序遍历：");
        this.headLastDFS(root);
        System.out.print("\n中序遍历：");
        this.headMiddleDFS(root);
    }

    private void headFirstDFS(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pollLast();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
    }

    private void headLastDFS(TreeNode root) {
        // 1. 用两个栈实现 -> 先把先序遍历的结果放到一个 help 栈中，然后在弹出打印
        // 2. 用一个栈实现
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.peekLast();
            if (node.left != null && node.left != root && node.right != root) {
                stack.offerLast(node.left);
            } else if (node.right != null && node.right != root) {
                stack.offerLast(node.right);
            } else {
                System.out.print(stack.pollLast().val + " ");
                root = node;
            }
        }
    }

    /**
     * 中序遍历：任何树都是可以被左边界分解的 -> 只要有左边界就把左边界压栈
     */
    private void headMiddleDFS(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        // 1、只要 left 不为空，就往死了压栈，直到 left 为空
        // 2、left 为空时，弹出栈顶打印，并将 right 压栈，重复 1
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.offerLast(node);
                node = node.left;
            } else {
                node = stack.pollLast();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
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
