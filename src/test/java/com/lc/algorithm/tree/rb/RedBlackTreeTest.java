package com.lc.algorithm.tree.rb;

import org.junit.Test;

public class RedBlackTreeTest {
    @Test
    public void insert() throws Exception {
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        rbTree.insert(11);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(8);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(18);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(16);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(20);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(15);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(14);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(13);
        rbTree.printTree();
    }

    @Test
    public void test() throws Exception {
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        rbTree.insert(11);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(9);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(14);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(7);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(10);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(15);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(5);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(8);
        rbTree.printTree();
        System.out.println("-----------------------------");
        rbTree.insert(4);
        rbTree.printTree();
        System.out.println("-----------------------------");
    }
}