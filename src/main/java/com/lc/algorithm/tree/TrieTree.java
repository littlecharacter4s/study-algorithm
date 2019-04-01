package com.lc.algorithm.tree;

public class TrieTree {
    /**
     * 注：如果这个不用固定大小，时间复杂度就会大增。空间换时间
     */
    private static final int SIZE = 26;
    private static final TrieNode ROOT = new TrieNode();
    private final StringBuilder sb = new StringBuilder();

    public void insert(String s) {
        if (s == null || "".equals(s)) {
            return;
        }
        TrieNode[] children = ROOT.getChildren();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            boolean isEnd = i == cs.length - 1;
            int index = cs[i] - 'a';
            if (children[index] == null) {
                children[index] = new TrieNode(cs[i], isEnd);
            } else {
                children[index].count += 1;
                if (isEnd) {
                    children[index].isEnd = true;
                    children[index].endCount += 1;
                }
            }
            children = children[index].getChildren();
        }
    }

    public void delete(String s) {
        if (s == null || "".equals(s)) {
            return;
        }
        if (!this.search(s)) {
            return;
        }
        TrieNode[] children = ROOT.getChildren();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            boolean isEnd = i == cs.length - 1;
            int index = cs[i] - 'a';
            if (children[index].count <= 1) {
                children[index] = null;
                return;
            } else {
                children[index].count -= 1;
                if (isEnd) {
                    children[index].endCount -= 1;
                    children[index].isEnd = children[index].endCount > 0;
                }
            }
            children = children[index].getChildren();
        }
    }

    public boolean search(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        TrieNode[] children = ROOT.getChildren();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            boolean isEnd = i == cs.length - 1;
            int index = cs[i] - 'a';
            if (children[index] == null) {
                return false;
            }
            if (isEnd) {
                return children[index].isEnd;
            }
            children = children[index].getChildren();
        }
        return false;
    }

    public TrieNode getRoot() {
        return ROOT;
    }

    public void traversal(TrieNode node) {
        if (node != ROOT) {
            sb.append(node.data);
        }
        TrieNode[] children = node.getChildren();
        boolean isLeaf = true;
        for (TrieNode child : children) {
            if (child != null) {
                isLeaf = false;
                this.traversal(child);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (isLeaf) {
            System.out.println(sb.toString());
        }
    }

    static class TrieNode {
        /**
         * 有多少字符串通过这个节点
         */
        private int count;
        /**
         * 是不是最后一个节点
         */
        private boolean isEnd;
        /**
         * 当前节点做为最后一个节点的个数
         */
        private int endCount;
        /**
         * 节点的值
         */
        private char data;
        /**
         * 所有的儿子节点
         */
        private TrieNode[] children;

        TrieNode() {
            this.count = 1;
            this.isEnd = false;
            this.endCount = 0;
            this.children = new TrieNode[SIZE];
        }

        TrieNode(char data) {
            this.count = 1;
            this.data = data;
            this.isEnd = false;
            this.endCount = 0;
            this.children = new TrieNode[SIZE];
        }

        TrieNode(char data, boolean isEnd) {
            this.count = 1;
            this.data = data;
            this.isEnd = isEnd;
            this.endCount = isEnd ? 1 : 0;
            this.children = new TrieNode[SIZE];
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public int getEndCount() {
            return endCount;
        }

        public void setEndCount(int endCount) {
            this.endCount = endCount;
        }

        public char getData() {
            return data;
        }

        public void setData(char data) {
            this.data = data;
        }

        public TrieNode[] getChildren() {
            return children;
        }

        public void setChildren(TrieNode[] children) {
            this.children = children;
        }
    }
}
