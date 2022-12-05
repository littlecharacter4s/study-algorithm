package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 题目：从前序与中序遍历序列构造二叉树
 * 描述：给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 分析：递归构建 + 宏观调度
 * 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * @author gujixian
 * @since 2022/12/5
 */
public class LC0105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode head = new LC0105ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
        // 输出结果
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            int ll = queue.size();
            for (int i = 0; i < ll; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (Objects.nonNull(node.left)) {
                    queue.offer(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return this.buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    private TreeNode buildTree(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        // 终止判断
        if (pLeft > pRight || iLeft > iRight) {
            return null;
        }
        // 构建节点
        TreeNode node = new TreeNode(preorder[pLeft]);
        int index = map.get(preorder[pLeft]);
        // 左右子树的边界举个简单的例子就可以很轻松写出，拒绝抽象，拥抱具象
        node.left = this.buildTree(preorder, pLeft + 1, pLeft + (index - iLeft), inorder, iLeft, index - 1);
        node.right = this.buildTree(preorder, pLeft + (index - iLeft) + 1, pRight, inorder, index + 1, iRight);
        return node;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
