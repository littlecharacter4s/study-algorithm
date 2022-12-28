package com.lc.structure.tree.trie;

import java.util.HashMap;

/**
 * @author gujixian
 * @since 2022/12/2
 */
public class TrieTree {
    private final Node root;

    public TrieTree() {
        root = new Node();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        node.pass++;
        int index = 0;//路径
        for (int i = 0; i < chars.length; i++) {
            index = (int) chars[i];
            if (!node.nexts.containsKey(index)) {//下一层的路径中不包括
                node.nexts.put(index, new Node());
            }
            node = node.nexts.get(index);//当前节点
            node.pass++;//当前节点++
        }
        //循环结束后，说明单词已经遍历完
        node.end++;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            Node node = root;
            int index = 0;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = (int) chars[i];
                if (--node.nexts.get(index).pass == 0) {
                    node.nexts.remove(index);//删除节点，防止内存泄露
                    return;
                }
                node = node.nexts.get(index);
            }
            node.end--;
        }
    }

    //word 这个单词之前加入了几次
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = (int) chars[i];
            if (!node.nexts.containsKey(index)) {
                return 0;
            }
            node = node.nexts.get(index);
        }
        return node.end;//有几个结尾，就加入了几次
    }

    //所有加入的字符串中，有几个是以pre 这个字符串作为前缀的
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!node.nexts.containsKey(index)) {
                return 0;
            }
            node = node.nexts.get(index);
        }
        return node.pass;//有几个单词经过 就有几个
    }

    public void traversal(Node node) {
    }

    public static class Node {
        public int pass;
        public int end;
        // 如果想有序，可以使用TreeMap：字符的 ASCII 码 -> nextNode
        public HashMap<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert("美国");
        tree.insert("日本");
        tree.insert("澳大利亚");
        tree.insert("澳洲");
        tree.insert("澳门");
        tree.delete("澳大利亚");
        System.out.println(tree.search("澳大利亚"));
    }
}
