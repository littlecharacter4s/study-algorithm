package com.lc.structure.order;

import java.util.Comparator;

/**
 * 红黑树
 *
 * @author gujixian
 * @since xxxx/xx/xx
 */
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
        while (node != NIL) {
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
            if (node.getColor() == RED) {
                //删除节点是红色节点时无需调整
                root.setColor(BLACK);
                return;
            } else {
                node = NIL;
                //删除节点是黑色节点时才调整树
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
                            //继续调整-这儿就是先把根节点设置为红色的原因
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
        System.out.println("E:" + node.getElement() + ", C:" + node.getColor() + ", L:" + node.getLeft().getElement() + ", R:" + node.getRight().getElement());
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
        while (minNode.getLeft() != NIL) {
            minNode = minNode.getLeft();
        }
        return minNode;
    }

    private RedBlackNode<E> max(RedBlackNode<E> maxNode) {
        while (maxNode.getRight() != NIL) {
            maxNode = maxNode.getRight();
        }
        return maxNode;
    }

    class RedBlackNode<E> {
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

    public static class RBTree<E extends Comparable<? super E>> {
        public static final boolean RED = true;
        public static final boolean BLACK = false;

        //所有叶子节点的左右子支都指向同一个NIL节点,NIL节点的父节点指向null
        private final Node<E> NIL;
        private final Node<E> ROOT;//指向伪根节点的引用
        private int size = 0;//节点个数
        Comparator<? super E> cmp;//节点大小的比较器

        //不带比较器的构造函数
        public RBTree() {
            ROOT = new Node<E>(null, BLACK, null, null, null);
            NIL = new Node<E>(null, BLACK, null, null, null);
            NIL.left = NIL;
            NIL.right = NIL;
            ROOT.left = NIL;
            ROOT.right = NIL;
            ROOT.parent = ROOT;
            cmp = new Cmp<E>();
        }

        //带比较器的构造函数
        public RBTree(Cmp<? super E> cmp) {
            if (cmp == null) {
                throw new IllegalArgumentException();
            }
            this.cmp = cmp;
            //创建一个伪根节点，该节点的右子支才是真正的RBtree树的根,同时该节点还作为NIL节点
            //使用伪根节点节点的目的是，对插入和删除操作递归的形式能够统一
            ROOT = new Node<E>(null, BLACK, null, null, null);
            NIL = new Node<E>(null, BLACK, null, null, null);
            NIL.left = NIL;
            NIL.right = NIL;
            ROOT.left = NIL;
            ROOT.right = NIL;
            ROOT.parent = ROOT;
        }

        //逆时针旋转（左旋），参数表示轴节点
        private void antiClockwiseRotate(Node<E> X) {
            Node<E> P = X.parent;
            Node<E> XR = X.right;
            if (P.left == X) {
                P.left = XR;
            } else {
                P.right = XR;
            }
            XR.parent = P;

            X.right = XR.left;
            if (XR.left != NIL) {
                XR.left.parent = X;
            }

            XR.left = X;
            X.parent = XR;
        }

        //顺时针旋转（右旋）,参数表示轴节点
        private void clockwiseRotate(Node<E> X) {
            Node<E> P = X.parent;
            Node<E> XL = X.left;
            if (P.left == X) {
                P.left = XL;
            } else {
                P.right = XL;
            }
            XL.parent = P;

            X.left = XL.right;
            if (XL.right != NIL) {
                XL.right.parent = X;
            }

            XL.right = X;
            X.parent = XL;
        }

        private Node<E> min(Node<E> X) {
            while (X.left != NIL) {
                X = X.left;
            }
            return X;
        }

        public int size() {
            return size;
        }

        public boolean contain(E e) {
            Node<E> X = ROOT.right;
            while (X != NIL) {
                int r = cmp.compare(e, X.e);
                if (r > 0) {
                    X = X.right;
                } else if (r < 0) {
                    X = X.left;
                } else {
                    return true;
                }
            }
            return false;
        }

        public boolean insert(E e) {
            Node<E> P = ROOT;
            Node<E> X = ROOT.right;
            int r = 0;
            while (X != NIL) {
                r = cmp.compare(e, X.e);
                P = X;
                if (r > 0) {
                    X = X.right;
                } else if (r < 0) {
                    X = X.left;
                } else {
                    return false;//元素已存在，插入失败
                }
            }

            Node<E> G;
            Node<E> U;
            X = new Node<E>(e, RED, NIL, NIL, P);//插入的新节点涂红
            if (r >= 0) {//考虑到首次插入的情况，这个等号是必须的
                P.right = X;
            } else {
                P.left = X;
            }

            while (true) {
                P = X.parent;

                //红父
                if (P.isRead()) {
                    G = P.parent;

                    if (P == G.left) {
                        U = G.right;
                    } else {
                        U = G.left;
                    }

                    //红叔
                    if (U.isRead()) {
                        P.color = BLACK;
                        U.color = BLACK;
                        G.color = RED;
                        X = G;//继续向上回溯
                    } else {//黑叔
                        if (G.left == P) {
                            if (P.left == X) {
                                clockwiseRotate(G);
                                P.color = BLACK;
                                G.color = RED;
                            } else {
                                antiClockwiseRotate(P);
                                clockwiseRotate(G);
                                X.color = BLACK;
                                G.color = RED;
                            }
                        } else {
                            if (P.right == X) {
                                antiClockwiseRotate(G);
                                P.color = BLACK;
                                G.color = RED;
                            } else {
                                clockwiseRotate(P);
                                antiClockwiseRotate(G);
                                X.color = BLACK;
                                G.color = RED;
                            }
                        }
                        break;
                    }
                } else {//黑父
                    break;
                }
            }
            size++;
            ROOT.right.color = BLACK;//有可能向上层进位，根节点图黑
            return true;
        }

        public boolean delete(E e) {
            Node<E> X = ROOT.right;
            X.color = RED; //删除时，根先涂红，1.防止继续向上回溯  2.只有根节点时也方便删除
            Node<E> P;
            Node<E> B;

            while (X != NIL) {
                int r = cmp.compare(e, X.e);
                if (r > 0) {
                    X = X.right;
                } else if (r < 0) {
                    X = X.left;
                } else {
                    break;
                }
            }

            if (X == NIL) {//没有找到需要删除的节点
                ROOT.right.color = BLACK;
                return false;
            }

            size--;//一定可以删除一个节点
            if (X.left != NIL && X.right != NIL) {
                Node<E> tmp = min(X.right);
                X.e = tmp.e;
                X = tmp;
            }

            P = X.parent;
            if (X.right != NIL) {
                if (X == P.left) {
                    P.left = X.right;
                } else {
                    P.right = X.right;
                }
                X.right.parent = P;
                X.color = BLACK;
                ROOT.right.color = BLACK;
                return true;
            } else if (X.left != NIL) {
                if (X == P.left) {
                    P.left = X.left;
                } else {
                    P.right = X.left;
                }
                X.left.parent = P;
                X.color = BLACK;
                ROOT.right.color = BLACK;
                return true;
            } else {
                if (X == P.left) {
                    P.left = NIL;
                } else {
                    P.right = NIL;
                }

                if (X.isRead()) {
                    ROOT.right.color = BLACK;
                    return true;
                } else {
                    X = NIL;
                }
            }

            //要删除的是叶子节点
            //四中情况调整
            while (true) {
                if (X == P.left) {
                    B = P.right;
                } else {
                    B = P.left;
                }

                if (!B.isRead()) {//黑兄
                    Node<E> BL = B.left;//左侄子
                    Node<E> BR = B.right;//右侄子
                    if (B.left.isRead() || B.right.isRead()) {//红侄
                        if (X == P.left) {
                            if (BR.isRead()) {
                                antiClockwiseRotate(P);
                                BR.color = BLACK;
                                B.color = P.color;
                                P.color = BLACK;
                            } else {
                                clockwiseRotate(B);
                                antiClockwiseRotate(P);
                                BL.color = P.color;
                                P.color = BLACK;
                            }
                        } else {
                            if (BL.isRead()) {
                                clockwiseRotate(P);
                                BL.color = BLACK;
                                B.color = P.color;
                                P.color = BLACK;
                            } else {
                                antiClockwiseRotate(B);
                                clockwiseRotate(P);
                                BR.color = P.color;
                                P.color = BLACK;
                            }
                        }
                        break;//不需要继续向上回溯
                    } else {
                        if (P.isRead()) {//黑侄红父
                            P.color = BLACK;
                            B.color = RED;
                            break;//不需要继续向上回溯
                        } else {//黑侄黑父,继续向上回溯
                            B.color = RED;
                            X = P;
                            P = X.parent;
                        }
                    }
                } else {//红兄，变换一下红黑树的形状，继续判断
                    if (B == P.right) {
                        antiClockwiseRotate(P);
                    } else {
                        clockwiseRotate(P);
                    }
                    B.color = BLACK;
                    P.color = RED;
                    //X节点的P节点没有发生变化，但兄弟节点发生变化
                }
            }
            ROOT.right.color = BLACK;
            return true;
        }

        public void preorderTraverse() {
            preorderTraverse0(ROOT.right);
        }

        private void preorderTraverse0(Node<E> X) {
            if (X != NIL) {
                System.out.print(X.e + "    " + (X.isRead() ? "RED  " : "BLACK") + "   :");
                if (X.left != NIL) {
                    System.out.print(X.left.e + "   ");
                } else {
                    System.out.print("NIL  ");
                }

                if (X.right != NIL) {
                    System.out.print(X.right.e + "   ");
                } else {
                    System.out.print("NIL  ");
                }
                System.out.println();
                preorderTraverse0(X.left);
                preorderTraverse0(X.right);
            }
        }

        private static class Node<E> {
            E e;
            boolean color;
            Node<E> left;
            Node<E> right;
            Node<E> parent;

            public Node(E e, boolean color, Node<E> left, Node<E> right, Node<E> parent) {
                this.e = e;
                this.color = color;
                this.left = left;
                this.right = right;
                this.parent = parent;
            }

            public boolean isRead() {
                return color;
            }
        }

        //如果调用了不带参数的构造函数，则使用该内部类作为比较器，
        //但此时泛型E需要继承Comparable接口,否则运行时会抛出异常
        private static class Cmp<T> implements Comparator<T> {
            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public int compare(T e1, T e2) {
                return ((Comparable) e1).compareTo(e2);
            }

        }

        public static void main(String[] args) {
            RBTree<Integer> rbt = new RBTree<Integer>();
            rbt.insert(11);
            rbt.insert(6);
            rbt.insert(15);
            rbt.insert(3);
            rbt.delete(3);
            rbt.delete(6);
            rbt.preorderTraverse();
            System.out.println();
            System.out.println("size: " + rbt.size());
            System.out.println(rbt.contain(40));
        }
    }
}
