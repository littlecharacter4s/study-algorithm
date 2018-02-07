package com.lc.algorithm.tree.avl;

public class AvlNode<E> {
    private E element;
    private AvlNode<E> left;
    private AvlNode<E> right;
    private int height;

    public AvlNode(E element) {
        this(element, null, null);
    }

    public AvlNode(E element, AvlNode<E> left, AvlNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public AvlNode<E> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<E> left) {
        this.left = left;
    }

    public AvlNode<E> getRight() {
        return right;
    }

    public void setRight(AvlNode<E> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
