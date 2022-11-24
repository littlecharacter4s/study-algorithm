package com.lc.algorithm;

import com.lc.algorithm.algorithm.lc.LC0102BinaryTreeLevelOrderTraversal;
import com.lc.algorithm.structure.tree.TreeNode;

import java.util.Random;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Integer> left = new TreeNode<>(2);
        TreeNode<Integer> right = new TreeNode<>(3);
        root.left = left;
        root.right = right;
        left.left = new TreeNode<>(4);
        left.right = new TreeNode<>(5);
        right.left = new TreeNode<>(6);
        right.right = new TreeNode<>(7);

        new Test().print(root);
    }

    public void print(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode<Integer>> stack = new Stack<>();
        this.pushLeft(stack, node);

        while (!stack.isEmpty()) {
            TreeNode<Integer> popNode = stack.pop();
            System.out.println(popNode.value);
            if (popNode.right != null) {
                this.pushLeft(stack, popNode.right);
            }
        }
    }

    private void pushLeft(Stack<TreeNode<Integer>> stack, TreeNode<Integer> node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }


}
