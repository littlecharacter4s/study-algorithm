package com.lc.algorithm.algorithm.lc;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class LC0102BinaryTreeLevelOrderTraversalTest {
    @Test
    public void testLevelOrder() throws Exception {
        LC0102BinaryTreeLevelOrderTraversal.TreeNode root = new LC0102BinaryTreeLevelOrderTraversal.TreeNode(3);
        LC0102BinaryTreeLevelOrderTraversal.TreeNode left = new LC0102BinaryTreeLevelOrderTraversal.TreeNode(9);
        LC0102BinaryTreeLevelOrderTraversal.TreeNode right = new LC0102BinaryTreeLevelOrderTraversal.TreeNode(20);
        root.left = left;
        root.right = right;
        right.left = new LC0102BinaryTreeLevelOrderTraversal.TreeNode(15);
        right.right = new LC0102BinaryTreeLevelOrderTraversal.TreeNode(7);
        List<List<Integer>> result = new LC0102BinaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(JSON.toJSONString(result));
    }
}