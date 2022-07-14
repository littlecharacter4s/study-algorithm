package com.lc.structure.tree;

import org.junit.Test;

public class TrieTreeTest {
    TrieTree trieTree = new TrieTree();

    @Test
    public void testInsert() throws Exception {
        trieTree.insert("abc");
        trieTree.insert("axy");
        trieTree.insert("bcd");
        trieTree.insert("cde");
        trieTree.traversal(trieTree.getRoot());
    }

    @Test
    public void testDelete() throws Exception {
        this.testInsert();
        trieTree.delete("axy");
        trieTree.traversal(trieTree.getRoot());
    }

    @Test
    public void testSearch() throws Exception {
        this.testInsert();
        System.out.println(trieTree.search("abc"));
    }
}