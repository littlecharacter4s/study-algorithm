package com.lc.algorithm.structure.tree;

import com.lc.algorithm.structure.tree.AvlTree;
import org.junit.Test;

public class AvlTreeTest {
    @Test
    public void insert() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(25);
        avlTree.insert(15);
        avlTree.insert(10);
        avlTree.insert(18);
        avlTree.printTreeMiddle();
    }

    @Test
    public void remove() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.insert(8);
        avlTree.insert(4);
        avlTree.insert(12);
        avlTree.insert(2);
        avlTree.insert(6);
        avlTree.insert(16);
        avlTree.insert(5);
        avlTree.printTreeMiddle();
        avlTree.remove(8);
        System.out.println("----------------------------------------------------");
        avlTree.printTreeMiddle();
    }
}