package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NC0002DeepFirstSearchN_aryTreeTest {
    private final int MAX_CHILDREND = 5;
    @Test
    public void testDeepOrder() throws Exception {
        NC0002DeepFirstSearchN_aryTree.Node root = new NC0002DeepFirstSearchN_aryTree.Node(1);
        this.addChildren(root);
        for (NC0002DeepFirstSearchN_aryTree.Node node : root.children) {
            this.addChildren(node);
        }
        List<List<Integer>> result = new NC0002DeepFirstSearchN_aryTree().deepOrder(root);
        System.out.println(JSON.toJSONString(result));
    }

    private void addChildren(NC0002DeepFirstSearchN_aryTree.Node root) {
        List<NC0002DeepFirstSearchN_aryTree.Node> children = new ArrayList<>();
        int size = new Random().nextInt(MAX_CHILDREND);
        for (int i = 0; i < size; i++) {
            children.add(new NC0002DeepFirstSearchN_aryTree.Node(new Random().nextInt(root.val * 2) + 1 + size));
        }
        root.children = children;
    }
}