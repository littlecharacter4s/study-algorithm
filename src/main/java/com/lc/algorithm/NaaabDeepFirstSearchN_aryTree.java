package com.lc.algorithm;

import com.lc.algorithm.stack.Stack;

import java.util.ArrayList;
import java.util.List;

public class NaaabDeepFirstSearchN_aryTree {
    public List<List<Integer>> deepOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        this.search(stack, result);
        return result;
    }

    private void search(Stack<Node> stack, List<List<Integer>> result) {
        Node top = stack.peek();
        if (top.children == null || top.children.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            stack.get().forEach(node -> list.add(node.val));
            result.add(list);
            stack.pop();
            return;
        }
        top.children.forEach(node -> {
            stack.push(node);
            this.search(stack, result);
        });
        stack.pop();
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
