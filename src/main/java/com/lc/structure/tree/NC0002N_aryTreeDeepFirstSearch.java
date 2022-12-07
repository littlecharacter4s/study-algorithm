package com.lc.structure.tree;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author gujixian
 * @since 2022/12/7
 */
public class NC0002N_aryTreeDeepFirstSearch {
    private final List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        Node root = new Node(1);
        root.children = Arrays.asList(new Node(2), new Node(3), new Node(4));
        root.children.get(0).children = Arrays.asList(new Node(5), new Node(6));
        root.children.get(1).children = Arrays.asList(new Node(7), new Node(8));
        root.children.get(2).children = Arrays.asList(new Node(9), new Node(10));

        List<List<Integer>> result = new NC0002N_aryTreeDeepFirstSearch().deepOrder(root);
        System.out.println(JSON.toJSONString(result));
    }

    public List<List<Integer>> deepOrder(Node root) {
        if(root == null){
            return result;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.offerLast(root);
        this.dfs(stack);
        return result;
    }

    private void dfs(Deque<Node> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Node top = stack.getLast();
        if (top.children == null || top.children.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            stack.forEach(node -> list.add(node.val));
            result.add(list);
            return;
        }
        top.children.forEach(node -> {
            stack.offerLast(node);
            this.dfs(stack);
            stack.pollLast();
        });
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
