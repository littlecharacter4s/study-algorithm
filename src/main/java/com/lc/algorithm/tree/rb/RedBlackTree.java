package com.lc.algorithm.tree.rb;

import com.alibaba.fastjson.JSON;

public class RedBlackTree<E extends Comparable<? super E>> {
    public static final int RED = 0;
    public static final int BLACK = 1;

    private RedBlackNode<E> root;

    public RedBlackTree() {
        root = new RedBlackNode<>(null, null, null, null, BLACK);
    }

    public void insert(E element) {
        this.insert(element, root);
    }

    private void insert(E element, RedBlackNode<E> node) {
        if(this.isNullNode(node)) {
            node.setElement(element);
            node.setLeft(new RedBlackNode<>(null, null, null, node, BLACK));
            node.setRight(new RedBlackNode<>(null, null, null, node, BLACK));
            node.setColor(node.getParent() == null ? BLACK : RED);
        }

        if (element.compareTo(node.getElement()) < 0) {
            this.insert(element, node.getLeft());
        } else if (element.compareTo(node.getElement()) > 0) {
            insert(element, node.getRight());
        }

        //必须在递归后获取父节点 - 因为子插入有可能旋转导致当前节点的父节点发生改变
        RedBlackNode<E> parent = node.getParent();
        //自下而上的调整过程中，根节点可能是红色，直接变成黑色即可
        if (parent == null) {
            node.setColor(BLACK);
            return;
        }
        //自下而上调整的过程中，当前节点是黑色，则什么呀不做
        if (node.getColor() == BLACK) {
            return;
        }
        //自下而上调整的过程中，当前节点是红色，且父节点是红色，调整
        if (parent.getColor() == RED) {
            RedBlackNode<E> grandNode = parent.getParent();
            if (grandNode != null) {
                RedBlackNode<E> uncleNode = grandNode.getLeft() == parent ? grandNode.getRight() : grandNode.getLeft();
                if (uncleNode.getColor() == RED) {
                    //CASE1：当前节点的父节点是红色，叔叔节点是红色
                    //1、将父节点设为黑色
                    //2、将叔叔节点设为黑色
                    //3、将祖父节点设为红色
                    parent.setColor(BLACK);
                    uncleNode.setColor(BLACK);
                    grandNode.setColor(RED);
                } else if (node == parent.getRight()) {
                    //CASE2：当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的右孩子
                    //1、将父节点作为新的当前节点
                    //2、以新的当前节点为支点进行左旋
                    this.leftRotate(parent);
                } else {
                    //CASE3：当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的左孩子
                    //1、将父节点设为黑色
                    //2、将祖父节点设为红色
                    //3、以祖父节点为支点进行右旋
                    parent.setColor(BLACK);
                    grandNode.setColor(RED);
                    this.rightRotate(grandNode);
                }
            }
        }
    }

    public void remove(E element) {
        this.insert(element, root);
    }

    public void remove(E element, RedBlackNode<E> node) {

    }

    public void removeAll() {
        root = new RedBlackNode<>(null, null, null, null, BLACK);
    }

    public void printTree() {
        this.printTree(root);
    }

    public void printTree(RedBlackNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(JSON.toJSONString(node));
        this.printTree(node.getLeft());
        this.printTree(node.getRight());
    }

    private boolean isNullNode(RedBlackNode<E> node) {
        return node.getElement() == null && node.getLeft() == null && node.getRight() == null;
    }

    private RedBlackNode<E> leftRotate(RedBlackNode<E> fulcrum) {
        RedBlackNode<E> newFulcrum = fulcrum.getRight();
        //成对改变
        RedBlackNode<E> parent = fulcrum.getParent();
        newFulcrum.setParent(parent);
        if (parent != null) {
            if (fulcrum == parent.getLeft()) {
                parent.setLeft(newFulcrum);
            } else {
                parent.setRight(newFulcrum);
            }
        }
        //成对改变
        fulcrum.setRight(newFulcrum.getLeft());
        newFulcrum.getLeft().setParent(fulcrum);
        //成对改变
        fulcrum.setParent(newFulcrum);
        newFulcrum.setLeft(fulcrum);

        //旋转可能产生新的根节点
        root = newFulcrum.getParent() == null ? newFulcrum : root;

        return newFulcrum;
    }

    private RedBlackNode<E> rightRotate(RedBlackNode<E> fulcrum) {
        RedBlackNode<E> newFulcrum = fulcrum.getLeft();
        //成对改变
        RedBlackNode<E> parent = fulcrum.getParent();
        newFulcrum.setParent(parent);
        if (parent != null) {
            if (fulcrum == parent.getLeft()) {
                parent.setLeft(newFulcrum);
            } else {
                parent.setRight(newFulcrum);
            }
        }
        //成对改变
        fulcrum.setLeft(newFulcrum.getRight());
        newFulcrum.getRight().setParent(fulcrum);
        //成对改变
        fulcrum.setParent(newFulcrum);
        newFulcrum.setRight(fulcrum);

        //旋转可能产生新的根节点
        root = newFulcrum.getParent() == null ? newFulcrum : root;

        return newFulcrum;
    }
}
