package com.lc.structure.tree;

import com.lc.structure.order.RedBlackTree;
import org.junit.Test;

public class RedBlackTreeTest {
    @Test
    public void testInsert() throws Exception {
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
    public void testRemove() throws Exception {
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        System.out.println("insert:11--------------------");
        rbTree.insert(11);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:8---------------------");
        rbTree.insert(8);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:18--------------------");
        rbTree.insert(18);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:16--------------------");
        rbTree.insert(16);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:20--------------------");
        rbTree.insert(20);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:15--------------------");
        rbTree.insert(15);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:14--------------------");
        rbTree.insert(14);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:13--------------------");
        rbTree.insert(13);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("remove:13--------------------");
        rbTree.remove(13);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:13--------------------");
        rbTree.insert(13);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("remove:14--------------------");
        rbTree.remove(14);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:14--------------------");
        rbTree.insert(14);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("remove:11--------------------");
        rbTree.remove(11);
        rbTree.printTree();
        System.out.println("-----------------------------");
        System.out.println("insert:11--------------------");
        rbTree.insert(11);
        rbTree.printTree();
        System.out.println("-----------------------------");
    }
}