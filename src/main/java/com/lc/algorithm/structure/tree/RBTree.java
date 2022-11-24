package com.lc.algorithm.structure.tree;

import java.util.Comparator;

public class RBTree<E extends Comparable<? super E>> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    //所有叶子节点的左右子支都指向同一个NIL节点,NIL节点的父节点指向null
    private final Node<E> NIL;
    private final Node<E> ROOT;//指向伪根节点的引用
    private int size = 0;//节点个数
    Comparator<? super E> cmp;//节点大小的比较器

    //不带比较器的构造函数
    public RBTree(){
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
    public RBTree(Cmp<? super E> cmp){
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
    private void antiClockwiseRotate(Node<E> X){
        Node<E> P = X.parent;
        Node<E> XR = X.right;
        if(P.left == X){
            P.left = XR;
        }else{
            P.right = XR;
        }
        XR.parent = P;

        X.right = XR.left;
        if(XR.left != NIL){
            XR.left.parent = X;
        }

        XR.left = X;
        X.parent = XR;
    }

    //顺时针旋转（右旋）,参数表示轴节点
    private void clockwiseRotate(Node<E> X){
        Node<E> P = X.parent;
        Node<E> XL = X.left;
        if(P.left == X){
            P.left = XL;
        }else{
            P.right = XL;
        }
        XL.parent = P;

        X.left = XL.right;
        if(XL.right != NIL){
            XL.right.parent = X;
        }

        XL.right = X;
        X.parent = XL;
    }

    private Node<E> min(Node<E> X){
        while(X.left != NIL){
            X = X.left;
        }
        return X;
    }

    public int size(){
        return size;
    }

    public boolean contain(E e){
        Node<E> X = ROOT.right;
        while(X != NIL){
            int r = cmp.compare(e, X.e);
            if(r > 0){
                X = X.right;
            }else
            if(r < 0){
                X = X.left;
            }else{
                return true;
            }
        }
        return false;
    }

    public boolean insert(E e){
        Node<E> P = ROOT;
        Node<E> X = ROOT.right;
        int r = 0;
        while(X != NIL){
            r = cmp.compare(e, X.e);
            P = X;
            if(r > 0){
                X = X.right;
            }else
            if(r < 0){
                X = X.left;
            }else{
                return false;//元素已存在，插入失败
            }
        }

        Node<E> G;
        Node<E> U;
        X = new Node<E>(e, RED, NIL, NIL, P);//插入的新节点涂红
        if(r >= 0){//考虑到首次插入的情况，这个等号是必须的
            P.right = X;
        }else{
            P.left = X;
        }

        while(true){
            P = X.parent;

            //红父
            if(P.isRead()){
                G = P.parent;

                if(P == G.left){
                    U = G.right;
                }else{
                    U = G.left;
                }

                //红叔
                if(U.isRead()){
                    P.color = BLACK;
                    U.color = BLACK;
                    G.color = RED;
                    X = G;//继续向上回溯
                }else{//黑叔
                    if(G.left == P){
                        if(P.left == X){
                            clockwiseRotate(G);
                            P.color = BLACK;
                            G.color = RED;
                        }else{
                            antiClockwiseRotate(P);
                            clockwiseRotate(G);
                            X.color = BLACK;
                            G.color = RED;
                        }
                    }else{
                        if(P.right == X){
                            antiClockwiseRotate(G);
                            P.color = BLACK;
                            G.color = RED;
                        }else{
                            clockwiseRotate(P);
                            antiClockwiseRotate(G);
                            X.color = BLACK;
                            G.color = RED;
                        }
                    }
                    break;
                }
            }else{//黑父
                break;
            }
        }
        size++;
        ROOT.right.color = BLACK;//有可能向上层进位，根节点图黑
        return true;
    }

    public boolean delete(E e){
        Node<E> X = ROOT.right;
        X.color = RED; //删除时，根先涂红，1.防止继续向上回溯  2.只有根节点时也方便删除
        Node<E> P;
        Node<E> B;

        while(X != NIL){
            int r = cmp.compare(e, X.e);
            if(r > 0){
                X = X.right;
            }else
            if(r < 0){
                X = X.left;
            }else{
                break;
            }
        }

        if(X == NIL){//没有找到需要删除的节点
            ROOT.right.color = BLACK;
            return false;
        }

        size--;//一定可以删除一个节点
        if(X.left != NIL && X.right != NIL){
            Node<E> tmp = min(X.right);
            X.e = tmp.e;
            X = tmp;
        }

        P = X.parent;
        if(X.right != NIL){
            if(X == P.left){
                P.left = X.right;
            }else{
                P.right = X.right;
            }
            X.right.parent = P;
            X.color = BLACK;
            ROOT.right.color = BLACK;
            return true;
        }else if(X.left != NIL){
            if(X == P.left){
                P.left = X.left;
            }else{
                P.right = X.left;
            }
            X.left.parent = P;
            X.color = BLACK;
            ROOT.right.color = BLACK;
            return true;
        }else{
            if(X == P.left){
                P.left = NIL;
            }else{
                P.right = NIL;
            }

            if(X.isRead()){
                ROOT.right.color = BLACK;
                return true;
            }else{
                X = NIL;
            }
        }

        //要删除的是叶子节点
        //四中情况调整
        while(true){
            if(X == P.left){
                B = P.right;
            }else{
                B = P.left;
            }

            if(!B.isRead()){//黑兄
                Node<E> BL = B.left;//左侄子
                Node<E> BR = B.right;//右侄子
                if(B.left.isRead() || B.right.isRead()){//红侄
                    if(X == P.left){
                        if(BR.isRead()){
                            antiClockwiseRotate(P);
                            BR.color = BLACK;
                            B.color = P.color;
                            P.color = BLACK;
                        }else{
                            clockwiseRotate(B);
                            antiClockwiseRotate(P);
                            BL.color = P.color;
                            P.color = BLACK;
                        }
                    }else{
                        if(BL.isRead()){
                            clockwiseRotate(P);
                            BL.color = BLACK;
                            B.color = P.color;
                            P.color = BLACK;
                        }else{
                            antiClockwiseRotate(B);
                            clockwiseRotate(P);
                            BR.color = P.color;
                            P.color = BLACK;
                        }
                    }
                    break;//不需要继续向上回溯
                }else{
                    if(P.isRead()){//黑侄红父
                        P.color = BLACK;
                        B.color = RED;
                        break;//不需要继续向上回溯
                    }else{//黑侄黑父,继续向上回溯
                        B.color = RED;
                        X = P;
                        P = X.parent;
                    }
                }
            }else{//红兄，变换一下红黑树的形状，继续判断
                if(B == P.right){
                    antiClockwiseRotate(P);
                }else{
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

    public void preorderTraverse(){
        preorderTraverse0(ROOT.right);
    }

    private void preorderTraverse0(Node<E> X){
        if(X != NIL){
            System.out.print(X.e + "    " + (X.isRead() ? "RED  " : "BLACK") + "   :");
            if(X.left != NIL){
                System.out.print(X.left.e + "   ");
            }else{
                System.out.print("NIL  ");
            }

            if(X.right != NIL){
                System.out.print(X.right.e + "   ");
            }else{
                System.out.print("NIL  ");
            }
            System.out.println();
            preorderTraverse0(X.left);
            preorderTraverse0(X.right);
        }
    }

    private static class Node<E>{
        E e;
        boolean color;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E e, boolean color, Node<E> left, Node<E> right, Node<E> parent){
            this.e = e;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public boolean isRead(){
            return color;
        }
    }

    //如果调用了不带参数的构造函数，则使用该内部类作为比较器，
    //但此时泛型E需要继承Comparable接口,否则运行时会抛出异常
    private static class Cmp<T> implements Comparator<T> {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public int compare(T e1, T e2) {
            return ((Comparable)e1).compareTo(e2);
        }

    }

    public static void main(String[] args){
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
