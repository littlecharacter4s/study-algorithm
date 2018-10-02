package com.lc.algorithm.tree;

import java.util.TreeMap;

public class TrieTree {
    public char insert(char value) {
        return ' ';
    }

    public char delete(char value) {
        return ' ';
    }

    private static class TrieNode {
        private boolean isWord;
        TreeMap<Character, TrieNode> children;

        public TrieNode() {}

        public TrieNode(boolean isWord) {
            this.isWord = isWord;
        }

        public TrieNode(boolean isWord, TreeMap<Character, TrieNode> children) {
            this.isWord = isWord;
            this.children = children;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }

        public TreeMap<Character, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(TreeMap<Character, TrieNode> children) {
            this.children = children;
        }
    }
}
