package com.lc.algorithm.tree.rb;

public class RedBlackNode<E> {
    private E element;
    private RedBlackNode<E> left;
    private RedBlackNode<E> right;
    private RedBlackNode<E> parent;
    private int color;

    public RedBlackNode(E element) {
        this(element, null, null, null);
    }

    public RedBlackNode(E element, RedBlackNode<E> left, RedBlackNode<E> right, RedBlackNode<E> parent) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.color = RedBlackTree.RED;
    }

    public RedBlackNode(E element, RedBlackNode<E> left, RedBlackNode<E> right, RedBlackNode<E> parent, int color) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.color = color;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public RedBlackNode<E> getLeft() {
        return left;
    }

    public void setLeft(RedBlackNode<E> left) {
        this.left = left;
    }

    public RedBlackNode<E> getRight() {
        return right;
    }

    public void setRight(RedBlackNode<E> right) {
        this.right = right;
    }

    public RedBlackNode<E> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<E> parent) {
        this.parent = parent;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
