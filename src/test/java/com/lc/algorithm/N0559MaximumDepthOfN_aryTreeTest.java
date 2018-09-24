package com.lc.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class N0559MaximumDepthOfN_aryTreeTest {
    private final int MAX_CHILDREND = 5;
    @Test
    public void testMaxDepth() throws Exception {
        N0559MaximumDepthOfN_aryTree.Node root = new N0559MaximumDepthOfN_aryTree.Node(1);
        this.addChildren(root);
        for (N0559MaximumDepthOfN_aryTree.Node node : root.children) {
            this.addChildren(node);
        }

        int result = new N0559MaximumDepthOfN_aryTree().maxDepth(root);
        System.out.println("最大深度：" + result);
    }

    private void addChildren(N0559MaximumDepthOfN_aryTree.Node root) {
        List<N0559MaximumDepthOfN_aryTree.Node> children = new ArrayList<>();
        int size = new Random().nextInt(MAX_CHILDREND);
        for (int i = 0; i < size; i++) {
            children.add(new N0559MaximumDepthOfN_aryTree.Node(new Random().nextInt(root.val * 2) + 1 + size));
        }
        root.children = children;
    }
}