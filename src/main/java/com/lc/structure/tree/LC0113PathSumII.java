package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：路径总和 II
 * 描述：
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 分析：递归遍历 + 当前节点先序加入路径，后序删除删除路径
 * 链接：https://leetcode.cn/problems/path-sum-ii/
 */
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

    }

    private static List<List<Integer>> result;
    private static List<Integer> subResult;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return result;
        }
        result = new LinkedList<>();
        subResult = new LinkedList<>();
        this.path(root, targetSum, 0);
        return result;
    }

    private void path(TreeNode node, int targetSum, int sum) {
        // 终止条件
        sum += node.val; // sum 不用还原是因为 sum 是值传递
        subResult.add(node.val);
        // 叶子节点 是指 没有子节点的节点
        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                result.add(new LinkedList(subResult));
            }
            // 一个递归调用结束时 remove 掉当前加入节点
            subResult.remove(subResult.size() - 1);
            return;
        }
        if (node.left != null) {
            this.path(node.left, targetSum, sum);
        }
        if (node.right != null) {
            this.path(node.right, targetSum, sum);
        }
        // 一个递归调用结束时 remove 掉当前加入节点
        subResult.remove(subResult.size() - 1);
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
