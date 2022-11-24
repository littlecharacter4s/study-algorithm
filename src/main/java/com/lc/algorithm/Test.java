package com.lc.algorithm;

import com.lc.algorithm.algorithm.lc.LC0102BinaryTreeLevelOrderTraversal;
import com.lc.algorithm.structure.tree.TreeNode;

import java.util.Random;

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
        // 递归序：第 1 次经过当前节点 -> 在这打印就是先序遍历
        print(node.left);
        // 递归序：第 2 次经过当前节点 -> 在这打印就是中序遍历
        print(node.right);
        // 递归序：第 3 次经过当前节点 -> 在这打印就是后序遍历
    }
}
