package com.lc.structure.tree;

import com.lc.structure.tree.trie.TrieTree;
import org.junit.Test;

public class TrieTreeTest {
    TrieTree trieTree = new TrieTree();

    @Test
    public void testInsert() throws Exception {
        trieTree.insert("abc");
        trieTree.insert("axy");
        trieTree.insert("bcd");
        trieTree.insert("cde");
        trieTree.traversal(new TrieTree.Node());
    }

    @Test
    public void testDelete() throws Exception {
        this.testInsert();
        trieTree.delete("axy");
        trieTree.traversal(new TrieTree.Node());
    }

    @Test
    public void testSearch() throws Exception {
        this.testInsert();
        System.out.println(trieTree.search("abc"));
    }
}