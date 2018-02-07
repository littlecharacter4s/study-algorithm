package com.lc.algorithm.tree.avl;

import com.alibaba.fastjson.JSON;

import java.util.Optional;

public class AvlTree<E extends Comparable<E>> {
    private AvlNode<E> root = null;

    public AvlTree() {}

    public AvlTree(AvlNode<E> root) {
        this.root = root;
    }

    public void insert(E element) {
        root = this.insert(element, root);
    }

    public void remove(E element) {
        root = this.remove(element, root);
    }

    public int height() {
        return this.height(root);
    }

    private AvlNode<E> insert(E element, AvlNode<E> root) {
        //插入的位置
        root = Optional.ofNullable(root).orElse(new AvlNode<>(element));
        if (element.compareTo(root.getElement()) < 0) {
            //插入到左子树
            root.setLeft(this.insert(element, root.getLeft()));
            //插入之后判断是否打破了平衡
            if (Math.abs(this.height(root.getLeft()) - this.height(root.getRight())) == 2) {
                if (element.compareTo(root.getLeft().getElement()) < 0) {
                    /**
                     * LL型
                     *****************************************
                     *           8       *           8       *
                     *      4         12 *      4         12 *
                     *   2     6         *   2     6         *
                     * x                 *     y             *
                     *****************************************
                     */
                    root = this.rightRotate(root);
                } else {
                    /**
                     * LR型
                     *****************************************
                     *           8       *           8       *
                     *      4         12 *      4         12 *
                     *   2     6         *   2     6         *
                     *       x           *           y       *
                     *****************************************
                     */
                    root = this.leftAndRightRotate(root);
                }
            }
        } else if (element.compareTo(root.getElement()) > 0) {
            //插入到右子树
            root.setRight(insert(element, root.getRight()));
            //插入之后判断是否打破了平衡
            if (Math.abs(this.height(root.getRight()) - this.height(root.getLeft())) == 2) {
                if (element.compareTo(root.getRight().getElement()) > 0) {
                    /**
                     * RR型
                     *****************************************
                     *      8             *      8           *
                     * 4         12       * 4         12     *
                     *       10      14   *       10      14 *
                     *                  x *             y    *
                     *****************************************
                     */
                    root = this.leftRotate(root);
                } else {
                    /**
                     * RL型
                     *****************************************
                     *      8             *      8           *
                     * 4         12       * 4         12     *
                     *       10      14   *       10      14 *
                     *     x              *          y       *
                     *****************************************
                     */
                    root = this.rightAndLeftRotate(root);
                }
            }
        }

        //设置节点的高度
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

        return root;
    }

    private AvlNode<E> remove(E element, AvlNode<E> root) {
        //找不到要删除的节点
        if (root == null) {
            return null;
        }
        if (element.compareTo(root.getElement()) < 0) {
            //要删除的节点在左子树中
            root.setLeft(this.remove(element, root.getLeft()));
            //删除之后判断是否打破了平衡
            if (Math.abs(this.height(root.getRight()) - this.height(root.getLeft())) == 2) {
                //因为删除的是左子树的节点，所以失衡情况只能是RX型
                AvlNode<E> rightNode = root.getRight();
                //根据右子树的左右深度判断失衡类型
                if (this.height(rightNode.getLeft()) > this.height(rightNode.getRight())) {
                    /**
                     * RL型
                     *****************************************
                     *      8             *      8           *
                     * 4         12       * 4         12     *
                     *       10      14   *       10      14 *
                     *     x              *          y       *
                     *****************************************
                     */
                    root = this.rightAndLeftRotate(root);
                } else {
                    /**
                     * RR型
                     *****************************************
                     *      8             *      8           *
                     * 4         12       * 4         12     *
                     *       10      14   *       10      14 *
                     *                  x *             y    *
                     *****************************************
                     */
                    root = this.leftRotate(root);
                }
            }
        } else if (element.compareTo(root.getElement()) > 0) {
            //要删除的节点在右子树中
            root.setRight(this.remove(element, root.getRight()));
            //删除之后判断是否打破了平衡
            if (Math.abs(this.height(root.getLeft()) - this.height(root.getRight())) == 2) {
                //因为删除的是右子树的节点，所以失衡情况只能是LX型
                AvlNode<E> leftNode = root.getLeft();
                //根据左子树的左右深度判断失衡类型
                if (this.height(leftNode.getRight()) > this.height(leftNode.getLeft())) {
                    /**
                     * LR型
                     *****************************************
                     *           8       *           8       *
                     *      4         12 *      4         12 *
                     *   2     6         *   2     6         *
                     *       x           *           y       *
                     *****************************************
                     */
                    root = this.leftAndRightRotate(root);
                } else {
                    /**
                     * LL型
                     *****************************************
                     *           8       *           8       *
                     *      4         12 *      4         12 *
                     *   2     6         *   2     6         *
                     * x                 *     y             *
                     *****************************************
                     */
                    root = this.rightRotate(root);
                }
            }
        } else if (root.getLeft() != null && root.getRight() != null) {
            //要删除的节点有两个子树，找子树中最大或最小的节点删除，是为了能最小限度地影响树的平衡
            if (this.height(root.getLeft()) > this.height(root.getRight())) {
                AvlNode<E> maxNode = this.maximum(root.getLeft());
                root.setElement(maxNode.getElement());
                root.setLeft(this.remove(maxNode.getElement(), root.getLeft()));
            } else {
                AvlNode<E> minNode = this.minimum(root.getRight());
                root.setElement(minNode.getElement());
                root.setRight(this.remove(minNode.getElement(), root.getRight()));
            }
        } else {
            //要删除的节点小于两个子树
            root = (root.getLeft() != null) ? root.getLeft() : root.getRight();
        }

        //设置节点的高度
        if (root != null) {
            root.setHeight(Math.max(this.height(root.getLeft()), this.height(root.getRight())) + 1);
        }

        return root;
    }

    //LL
    private AvlNode<E> rightRotate(AvlNode<E> root) {
        AvlNode<E> newRoot = root.getLeft();
        root.setLeft(newRoot.getRight());
        newRoot.setRight(root);
        root.setHeight(Math.max(this.height(root.getLeft()), this.height(root.getRight())) + 1);
        newRoot.setHeight(Math.max(height(newRoot.getLeft()), height(newRoot.getRight())) + 1);
        return newRoot;
    }

    //RR
    private AvlNode<E> leftRotate(AvlNode<E> root) {
        AvlNode<E> newRoot = root.getRight();
        root.setRight(newRoot.getLeft());
        newRoot.setLeft(root);
        root.setHeight(Math.max(this.height(root.getLeft()), this.height(root.getRight())) + 1);
        newRoot.setHeight(Math.max(height(newRoot.getLeft()), height(newRoot.getRight())) + 1);
        return newRoot;
    }

    //LR
    private AvlNode<E> leftAndRightRotate(AvlNode<E> root) {
        root.setLeft(this.leftRotate(root.getLeft()));
        return rightRotate(root);
    }

    //RL
    private AvlNode<E> rightAndLeftRotate(AvlNode<E> root) {
        root.setRight(this.rightRotate(root.getRight()));
        return leftRotate(root);
    }

    private int height(AvlNode<E> root) {
        return root == null ? 0 : root.getHeight();
    }

    private AvlNode<E> minimum(AvlNode<E> root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }

        return root;
    }

    private AvlNode<E> maximum(AvlNode<E> root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }

        return root;
    }

    public void printTreeMiddle() {
        this.printTreeMiddle(root);
    }

    public void printTreeMiddle(AvlNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(JSON.toJSONString(node));
        this.printTreeMiddle(node.getLeft());
        this.printTreeMiddle(node.getRight());

    }

    public void printTreeLeft() {
        this.printTreeLeft(root);
    }

    public void printTreeLeft(AvlNode<E> node) {
        if (node == null) {
            return;
        }
        this.printTreeLeft(node.getLeft());
        System.out.println(JSON.toJSONString(node));
        this.printTreeLeft(node.getRight());

    }

    public void printTreeRight() {
        this.printTreeRight(root);
    }

    public void printTreeRight(AvlNode<E> node) {
        if (node == null) {
            return;
        }
        this.printTreeRight(node.getRight());
        System.out.println(JSON.toJSONString(node));
        this.printTreeRight(node.getLeft());
    }
}
