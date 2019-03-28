package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class N0113PathSumIITest {
    @Test
    public void testPathSum() throws Exception {
        N0113PathSumII.TreeNode root = new N0113PathSumII.TreeNode(5);
        N0113PathSumII.TreeNode l1001 = new N0113PathSumII.TreeNode(4);
        N0113PathSumII.TreeNode l1002 = new N0113PathSumII.TreeNode(8);
        root.left = l1001;
        root.right = l1002;
        N0113PathSumII.TreeNode l2001 = new N0113PathSumII.TreeNode(11);
        N0113PathSumII.TreeNode l2002 = new N0113PathSumII.TreeNode(13);
        N0113PathSumII.TreeNode l2003 = new N0113PathSumII.TreeNode(4);
        l1001.left = l2001;
        l1002.left = l2002;
        l1002.right = l2003;
        N0113PathSumII.TreeNode l3001 = new N0113PathSumII.TreeNode(7);
        N0113PathSumII.TreeNode l3002 = new N0113PathSumII.TreeNode(2);
        N0113PathSumII.TreeNode l3003 = new N0113PathSumII.TreeNode(5);
        N0113PathSumII.TreeNode l3004 = new N0113PathSumII.TreeNode(1);
        l2001.left = l3001;
        l2001.right = l3002;
        l2003.left = l3003;
        l2003.right = l3004;
        List<List<Integer>> result = new N0113PathSumII().pathSum(root, 22);
        System.out.println(JSON.toJSONString(result));
    }
}