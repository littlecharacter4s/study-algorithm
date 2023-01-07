package com.lc.structure.order;

import java.util.TreeMap;

/**
 * SB 树（SizeBalancedTree）
 *
 * @author gujixian
 * @since 2023/1/2
 */
public class SizeBalancedTree<K extends Comparable<K>, V> {
    private Node<K, V> root;


    public void put(K key, V value) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            lastNode.value = value;
        } else {
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        return lastNode != null && key.compareTo(lastNode.key) == 0;
    }

    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            return lastNode.value;
        } else {
            return null;
        }
    }

    public K getIndexKey(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        return getIndex(root, index + 1).key;
    }

    public V getIndexValue(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        return getIndex(root, index + 1).value;
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        Node<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public K floorKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        Node<K, V> lastNoBigNode = findLastSmallerOrEqualNode(key);
        return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        Node<K, V> lastNoSmallNode = findFirstBiggerOrEqualNode(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }

    public int size() {
        return root == null ? 0 : root.size;
    }


    // 现在，以cur为头的树上，新增，加(key, value)这样的记录
    // 加完之后，会对cur做检查，该调整调整
    // 返回，调整完之后，整棵树的新头部
    private Node<K, V> add(Node<K, V> cur, K key, V value) {
        if (cur == null) {
            return new Node<K, V>(key, value);
        } else {
            cur.size++;
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            } else {
                cur.right = add(cur.right, key, value);
            }
            return maintain(cur);
        }
    }

    // 在cur这棵树上，删掉key所代表的节点
    // 返回cur这棵树的新头部
    private Node<K, V> delete(Node<K, V> cur, K key) {
        cur.size--;
        if (key.compareTo(cur.key) > 0) {
            cur.right = delete(cur.right, key);
        } else if (key.compareTo(cur.key) < 0) {
            cur.left = delete(cur.left, key);
        } else { // 当前要删掉cur
            if (cur.left == null && cur.right == null) {
                // 无左无右
                cur = null;
            } else if (cur.left == null && cur.right != null) {
                // 无左有右
                cur = cur.right;
            } else if (cur.left != null && cur.right == null) {
                // 有左无右
                cur = cur.left;
            } else {
                // 有左有右 -> 找到后继节点删掉
                Node<K, V> pre = null;
                Node<K, V> des = cur.right;
                des.size--;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size--;
                }
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right;
                }
                des.left = cur.left;
                des.size = des.left.size + (des.right == null ? 0 : des.right.size) + 1;
                cur = des;
            }
        }
        // cur = maintain(cur); // 删除的时候可以允许不调整
        return cur;
    }

    private Node<K, V> getIndex(Node<K, V> cur, int kth) {
        if (kth == (cur.left != null ? cur.left.size : 0) + 1) {
            return cur;
        } else if (kth <= (cur.left != null ? cur.left.size : 0)) {
            return getIndex(cur.left, kth);
        } else {
            return getIndex(cur.right, kth - (cur.left != null ? cur.left.size : 0) - 1);
        }
    }

    // 查找节点，若存在，则返回，否则返回 key 应该在的位置的父节点
    private Node<K, V> findNodeOrPre(K key) {
        Node<K, V> ans = root;
        Node<K, V> cur = root;
        while (cur != null) {
            ans = cur;
            if (key.compareTo(cur.key) == 0) {
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }

    // 查找大于等于 key 的最小节点
    private Node<K, V> findFirstBiggerOrEqualNode(K key) {
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                ans = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }

    // 查找小于等于 key 的最大节点
    private Node<K, V> findLastSmallerOrEqualNode(K key) {
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                ans = cur;
                cur = cur.right;
            }
        }
        return ans;
    }

    private Node<K, V> maintain(Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left != null ? cur.left.size : 0;
        int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
        int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
        int rightSize = cur.right != null ? cur.right.size : 0;
        int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
        int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
        // LL、LR、RR、RL
        if (leftLeftSize > rightSize) {
            // LL
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            // LR
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            // RR
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            // RL
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    private Node<K, V> leftRotate(Node<K, V> cur) {
        Node<K, V> rightNode = cur.right;
        cur.right = rightNode.left;
        rightNode.left = cur;
        rightNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return rightNode;
    }

    private Node<K, V> rightRotate(Node<K, V> cur) {
        Node<K, V> leftNode = cur.left;
        cur.left = leftNode.right;
        leftNode.right = cur;
        leftNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return leftNode;
    }


    public static class Node<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        // 树的节点数
        public int size;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }


    public static void main(String[] args) {
        TreeMap treeMap;
        SizeBalancedTree<String, Integer> sbTree = new SizeBalancedTree<>();
        sbTree.put("a", 1);
        sbTree.put("b", 2);
        sbTree.put("c", 3);
        sbTree.put("d", 4);
        sbTree.put("e", 5);
        sbTree.put("f", 6);
        printAll(sbTree.root);
    }

    // for test
    public static void printAll(Node<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // for test
    public static void printInOrder(Node<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + "(" + head.key + "," + head.value + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    // for test
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
}
