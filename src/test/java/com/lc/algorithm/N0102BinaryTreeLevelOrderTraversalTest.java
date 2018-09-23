package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class N0102BinaryTreeLevelOrderTraversalTest {
    @Test
    public void testLevelOrder() throws Exception {
        N0102BinaryTreeLevelOrderTraversal.TreeNode root = new N0102BinaryTreeLevelOrderTraversal.TreeNode(3);
        N0102BinaryTreeLevelOrderTraversal.TreeNode left = new N0102BinaryTreeLevelOrderTraversal.TreeNode(9);
        N0102BinaryTreeLevelOrderTraversal.TreeNode right = new N0102BinaryTreeLevelOrderTraversal.TreeNode(20);
        root.left = left;
        root.right = right;
        right.left = new N0102BinaryTreeLevelOrderTraversal.TreeNode(15);
        right.right = new N0102BinaryTreeLevelOrderTraversal.TreeNode(7);
        List<List<Integer>> result = new N0102BinaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(JSON.toJSONString(result));
    }
}