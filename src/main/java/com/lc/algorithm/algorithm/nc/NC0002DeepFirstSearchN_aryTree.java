package com.lc.algorithm.algorithm.nc;

import com.lc.algorithm.structure.stack.ArrayStack;

import java.util.ArrayList;
import java.util.List;

public class NC0002DeepFirstSearchN_aryTree {
    public List<List<Integer>> deepOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        ArrayStack<Node> arrayStack = new ArrayStack<>();
        arrayStack.push(root);
        this.search(arrayStack, result);
        return result;
    }

    private void search(ArrayStack<Node> arrayStack, List<List<Integer>> result) {
        Node top = arrayStack.peek();
        if (top.children == null || top.children.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            arrayStack.get().forEach(node -> list.add(node.val));
            result.add(list);
            arrayStack.pop();
            return;
        }
        top.children.forEach(node -> {
            arrayStack.push(node);
            this.search(arrayStack, result);
        });
        arrayStack.pop();
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
