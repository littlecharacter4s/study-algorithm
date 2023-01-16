package com.lc.structure.order;

import com.alibaba.fastjson.JSON;

/**
 * 高效集合：add、remove、get 都是 O(logN) 复杂度
 *
 * @author gujixian
 * @since 2023/1/16
 */
public class EfficientList<V> {
    private Node<V> root;


    public void add(int index, V value) {
        Node<V> curr = new Node<>(value);
        if (root == null) {
            root = curr;
            return;
        }

        if (index > root.size) {
            throw new RuntimeException("数组越界");
        }
        root = insert(root, index, curr);
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("数组越界");
        }
        root = delete(root, index);
    }
    
    public V get(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("数组越界");
        }
        return select(root, index).value;
    }
    
    public int size() {
        return root == null ? 0 : root.size;
    }


    private Node<V> insert(Node<V> node, int index, Node<V> curr) {
        if (node == null) {
            return curr;
        }
        node.size++;
        int leftAndHeadSize = (node.left != null ? node.left.size : 0) + 1;
        if (index < leftAndHeadSize) {
            node.left = insert(node.left, index, curr);
        } else {
            node.right = insert(node.right, index - leftAndHeadSize, curr);
        }
        node = maintain(node);
        return node;
    }

    private Node<V> delete(Node<V> node, int index) {
        node.size--;
        int rootIndex = node.left != null ? node.left.size : 0;
        if (index != rootIndex) {
            if (index < rootIndex) {
                node.left = delete(node.left, index);
            } else {
                node.right = delete(node.right, index - rootIndex - 1);
            }
            return node;
        }
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        Node<V> prev = null;
        Node<V> dest = node.right;
        dest.size--;
        while (dest.left != null) {
            prev = dest;
            dest = dest.left;
            dest.size--;
        }
        if (prev != null) {
            prev.left = dest.right;
            dest.right = node.right;
        }
        dest.left = node.left;
        dest.size = dest.left.size + (dest.right == null ? 0 : dest.right.size) + 1;
        return dest;
    }

    private Node<V> select(Node<V> node, int index) {
        int leftSize = node.left != null ? node.left.size : 0;
        if (index < leftSize) {
            return select(node.left, index);
        } else if (index == leftSize) {
            return node;
        } else {
            return select(node.right, index - leftSize - 1);
        }
    }

    private Node<V> maintain(Node<V> curr) {
        if (curr == null) {
            return null;
        }
        int leftSize = curr.left != null ? curr.left.size : 0;
        int leftLeftSize = curr.left != null && curr.left.left != null ? curr.left.left.size : 0;
        int leftRightSize = curr.left != null && curr.left.right != null ? curr.left.right.size : 0;
        int rightSize = curr.right != null ? curr.right.size : 0;
        int rightLeftSize = curr.right != null && curr.right.left != null ? curr.right.left.size : 0;
        int rightRightSize = curr.right != null && curr.right.right != null ? curr.right.right.size : 0;
        if (leftLeftSize > rightSize) {
            curr = rightRotate(curr);
            curr.right = maintain(curr.right);
            curr = maintain(curr);
        } else if (leftRightSize > rightSize) {
            curr.left = leftRotate(curr.left);
            curr = rightRotate(curr);
            curr.left = maintain(curr.left);
            curr.right = maintain(curr.right);
            curr = maintain(curr);
        } else if (rightRightSize > leftSize) {
            curr = leftRotate(curr);
            curr.left = maintain(curr.left);
            curr = maintain(curr);
        } else if (rightLeftSize > leftSize) {
            curr.right = rightRotate(curr.right);
            curr = leftRotate(curr);
            curr.left = maintain(curr.left);
            curr.right = maintain(curr.right);
            curr = maintain(curr);
        }
        return curr;
    }

    private Node<V> rightRotate(Node<V> curr) {
        Node<V> leftNode = curr.left;
        curr.left = leftNode.right;
        leftNode.right = curr;
        leftNode.size = curr.size;
        curr.size = (curr.left != null ? curr.left.size : 0) + (curr.right != null ? curr.right.size : 0) + 1;
        return leftNode;
    }

    private Node<V> leftRotate(Node<V> curr) {
        Node<V> rightNode = curr.right;
        curr.right = rightNode.left;
        rightNode.left = curr;
        rightNode.size = curr.size;
        curr.size = (curr.left != null ? curr.left.size : 0) + (curr.right != null ? curr.right.size : 0) + 1;
        return rightNode;
    }


    private static class Node<V> {
        public V value;
        public Node<V> left;
        public Node<V> right;
        public int size;

        public Node(V v) {
            value = v;
            size = 1;
        }
    }

    public static void main(String[] args) {
        EfficientList<Integer> list = new EfficientList<>();
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < nums.length; i++) {
            list.add(i, nums[i]);
        }
        print(list.root);
        System.out.println("\n" + list.get(3));
        list.remove(3);
        print(list.root);
        System.out.println("\n" + list.get(3));
    }

    private static void print(Node node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.print("(" + node.value + ":" + node.size + ") ");
        print(node.right);
    }
}
