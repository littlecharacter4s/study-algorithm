package com.lc.algorithm.tree.rb;

import com.alibaba.fastjson.JSON;

public class RedBlackTree<E extends Comparable<? super E>> {
    public static final int RED = 0;
    public static final int BLACK = 1;

    private final RedBlackNode<E> NIL;
    private RedBlackNode<E> root;

    public RedBlackTree() {
        NIL = new RedBlackNode<>(null, null, null, null, BLACK);
        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        root = new RedBlackNode<>(null, null, null, null, BLACK);
        root.setLeft(NIL);
        root.setRight(NIL);
    }

    public void insert(E element) {
        if (root.getElement() == null) {
            root.setElement(element);
            return;
        }

        RedBlackNode<E> parent = root;
        RedBlackNode<E> node = root;

        while (node != NIL) {
            parent = node;
            if (element.compareTo(node.getElement()) < 0) {
                node = node.getLeft();
            } else if (element.compareTo(node.getElement()) > 0) {
                node = node.getRight();
            } else {
                return;
            }
        }

        node = new RedBlackNode<E>(element, NIL, NIL, parent, RED);
        if (element.compareTo(parent.getElement()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }

        while (true) {
            parent = node.getParent();
            if (parent == null) {
                break;
            }
            if (parent.getColor() == BLACK) {
                break;
            }
            RedBlackNode<E> grandNode = parent.getParent();
            RedBlackNode<E> uncleNode = grandNode.getLeft() == parent ? grandNode.getRight() : grandNode.getLeft();
            if (uncleNode.getColor() == RED) {
                //CASE1：父红叔红
                //1、将父节点设为黑色
                //2、将叔叔节点设为黑色
                //3、将祖父节点设为红色
                parent.setColor(BLACK);
                uncleNode.setColor(BLACK);
                grandNode.setColor(RED);
                node = grandNode;
            } else {
                if (parent == grandNode.getLeft()) {
                    if (node == parent.getLeft()) {
                        //CASE2：父红叔黑-LL
                        //1.以祖父节点为支点进行右旋；
                        //2.将父节点置为黑色；
                        //3.将祖父节点置为红色；
                        this.rightRotate(grandNode);
                        parent.setColor(BLACK);
                        grandNode.setColor(RED);
                    } else {
                        //CASE2：父红叔黑-LR
                        //1.以父节点为支点进行左旋；
                        //2.以祖父节点为支点进行右旋；
                        //3.将当前节点置为黑色；
                        //4.将祖父节点置为红色；
                        this.leftRotate(parent);
                        this.rightRotate(grandNode);
                        node.setColor(BLACK);
                        grandNode.setColor(RED);
                    }
                } else {
                    if (node == parent.getRight()) {
                        //CASE2：父红叔黑-RR
                        //1.以祖父节点为支点进行左旋；
                        //2.将父节点置为黑色；
                        //3.将祖父节点置为红色；
                        this.leftRotate(grandNode);
                        parent.setColor(BLACK);
                        grandNode.setColor(RED);
                    } else {
                        //CASE2：父红叔黑-RL
                        //1.以父节点为支点进行右旋；
                        //2.以祖父节点为支点进行左旋；
                        //3.将当前节点置为黑色；
                        //4.将祖父节点置为红色；
                        this.rightRotate(parent);
                        this.leftRotate(grandNode);
                        node.setColor(BLACK);
                        grandNode.setColor(RED);
                    }
                }
                break;
            }
        }

        root.setColor(BLACK);
    }

    public void remove(E element) {
        //根节点涂红：1.防止继续向上回溯；2.只有根节点时也方便删除
        root.setColor(RED);
        if (root.getElement() == null) {
            root.setColor(BLACK);
            return;
        }
        RedBlackNode<E> node = root;
        //找要删除的节点
        while(node != NIL){
            if (element.compareTo(node.getElement()) < 0) {
                node = node.getLeft();
            } else if (element.compareTo(node.getElement()) > 0) {
                node = node.getRight();
            } else {
                break;
            }
        }
        //没找到要删除的节点
        if (node == NIL) {
            root.setColor(BLACK);
            return;
        }

        if (node.getLeft() != NIL && node.getRight() != NIL) {
            RedBlackNode<E> minNode = this.min(node.getRight());
            RedBlackNode<E> maxNode = this.max(node.getLeft());
            //若能删除红色节点，则优先删除红色节点
            RedBlackNode<E> deleteNode = minNode.getColor() == RED ? minNode : maxNode;
            node.setElement(deleteNode.getElement());
            node = deleteNode;
        }

        RedBlackNode<E> parent = node.getParent();
        if (node.getLeft() == NIL && node.getRight() == NIL) {
            //子节点都为NIL的情况
            if (node == parent.getLeft()) {
                parent.setLeft(NIL);
            } else {
                parent.setRight(NIL);
            }
            //只有删除节点是黑色节点时才调整树
            if (node.getColor() == BLACK) {
                while (true) {
                    RedBlackNode<E> brother = node == parent.getLeft() ? parent.getRight() : parent.getLeft();
                    RedBlackNode<E> leftNephew = brother.getLeft();
                    RedBlackNode<E> rightNephew = brother.getRight();
                    if (brother.getColor() == BLACK) {
                        if (leftNephew.getColor() == RED || rightNephew.getColor() == RED) {
                            //黑兄红侄
                            if (node == parent.getLeft()) {
                                if (leftNephew.getColor() == RED) {
                                    this.rightRotate(brother);
                                    this.leftRotate(parent);
                                    leftNephew.setColor(parent.getColor());
                                    parent.setColor(BLACK);
                                } else {
                                    this.leftRotate(parent);
                                    rightNephew.setColor(BLACK);
                                    brother.setColor(parent.getColor());
                                    parent.setColor(BLACK);
                                }
                            } else {
                                if (leftNephew.getColor() == RED) {
                                    this.rightRotate(parent);
                                    leftNephew.setColor(BLACK);
                                    brother.setColor(parent.getColor());
                                    parent.setColor(BLACK);
                                } else {
                                    this.leftRotate(brother);
                                    this.rightRotate(parent);
                                    rightNephew.setColor(parent.getColor());
                                    parent.setColor(BLACK);
                                }
                            }
                            break;//调整结束
                        } else if (parent.getColor() == RED) {
                            //黑兄黑侄红父
                            parent.setColor(BLACK);
                            brother.setColor(RED);
                            break;//调整结束
                        } else {
                            //黑兄黑侄黑父
                            brother.setColor(RED);
                            node = parent;
                            parent = node.getParent();
                            //继续调整
                        }
                    } else {
                        //红兄(黑侄黑父)：变换一下红黑树的形状，回到黑兄的情况
                        if (node == parent.getLeft()) {
                            this.leftRotate(parent);
                        } else {
                            this.rightRotate(parent);
                        }
                        brother.setColor(BLACK);
                        parent.setColor(RED);
                        //继续调整
                    }
                }
            }
        } else if (node.getLeft() != NIL) {
            //子节点只有一个是NIL-子节点一定为红色，当前节点一定为黑色
            node.setElement(node.getLeft().getElement());
            node.setLeft(NIL);
        } else {
            //子节点只有一个是NIL-子节点一定为红色，当前节点一定为黑色
            node.setElement(node.getRight().getElement());
            node.setRight(NIL);
        }
        root.setColor(BLACK);
    }

    public void removeAll() {
        root = new RedBlackNode<>(null, null, null, null, BLACK);
        root.setLeft(NIL);
        root.setRight(NIL);
    }

    public void printTree() {
        this.printTree(root);
    }

    public void printTree(RedBlackNode<E> node) {
        if (node == NIL) {
            return;
        }
        System.out.println(JSON.toJSONString(node));
        this.printTree(node.getLeft());
        this.printTree(node.getRight());
    }

    private void leftRotate(RedBlackNode<E> fulcrum) {
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
        if (newFulcrum.getLeft() != NIL) {
            newFulcrum.getLeft().setParent(fulcrum);
        }
        //成对改变
        fulcrum.setParent(newFulcrum);
        newFulcrum.setLeft(fulcrum);

        //旋转可能产生新的根节点
        root = newFulcrum.getParent() == null ? newFulcrum : root;
    }

    private void rightRotate(RedBlackNode<E> fulcrum) {
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
        if (newFulcrum.getRight() != NIL) {
            newFulcrum.getRight().setParent(fulcrum);
        }
        //成对改变
        fulcrum.setParent(newFulcrum);
        newFulcrum.setRight(fulcrum);

        //旋转可能产生新的根节点
        root = newFulcrum.getParent() == null ? newFulcrum : root;
    }

    private RedBlackNode<E> min(RedBlackNode<E> minNode) {
        while(minNode.getLeft() != NIL){
            minNode = minNode.getLeft();
        }
        return minNode;
    }

    private RedBlackNode<E> max(RedBlackNode<E> maxNode) {
        while(maxNode.getRight() != NIL){
            maxNode = maxNode.getRight();
        }
        return maxNode;
    }
}
