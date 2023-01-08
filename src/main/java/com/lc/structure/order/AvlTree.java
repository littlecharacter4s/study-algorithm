package com.lc.structure.order;

import com.alibaba.fastjson.JSON;

/**
 * AVL 树
 *
 * @author gujixian
 * @since 2023/1/2
 */
public class AvlTree<K extends Comparable<K>, V> {
    private Node<K, V> root = null;
    private int size;


    public AvlTree() {
        root = null;
        size = 0;
    }

    
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            lastNode.value = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> lastNode = findNodeOrPre(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            return lastNode.value;
        }
        return null;
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
            return null;
        }
        Node<K, V> lastNoBigNode = findLastSmallerOrEqualNode(key);
        return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> lastNoSmallNode = findFirstBiggerOrEqualNode(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }

    public int size() {
        return size;
    }


    private Node<K, V> add(Node<K, V> cur, K key, V value) {
        if (cur == null) {
            return new Node<K, V>(key, value);
        } else {
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            } else {
                cur.right = add(cur.right, key, value);
            }
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            return maintain(cur);
        }
    }

    // 在cur这棵树上，删掉key所代表的节点
    // 返回cur这棵树的新头部
    private Node<K, V> delete(Node<K, V> cur, K key) {
        if (key.compareTo(cur.key) > 0) {
            cur.right = delete(cur.right, key);
        } else if (key.compareTo(cur.key) < 0) {
            cur.left = delete(cur.left, key);
        } else {
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
                Node<K, V> des = cur.right;
                while (des.left != null) {
                    des = des.left;
                }
                cur.right = delete(cur.right, des.key);
                des.left = cur.left;
                des.right = cur.right;
                cur = des;
            }
        }
        if (cur != null) {
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
        }
        return maintain(cur);
    }

    // 查找节点，若存在，则返回，否则返回 key 应该在的位置的父节点
    private Node<K, V> findNodeOrPre(K key) {
        Node<K, V> pre = root;
        Node<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) == 0) {
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
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
        int leftHeight = cur.left != null ? cur.left.height : 0;
        int rightHeight = cur.right != null ? cur.right.height : 0;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {
                int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                if (leftLeftHeight >= leftRightHeight) {
                    cur = rightRotate(cur);
                } else {
                    cur.left = leftRotate(cur.left);
                    cur = rightRotate(cur);
                }
            } else {
                int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                if (rightRightHeight >= rightLeftHeight) {
                    cur = leftRotate(cur);
                } else {
                    cur.right = rightRotate(cur.right);
                    cur = leftRotate(cur);
                }
            }
        }
        return cur;
    }

    private Node<K, V> rightRotate(Node<K, V> cur) {
        Node<K, V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
        left.height = Math.max((left.left != null ? left.left.height : 0), (left.right != null ? left.right.height : 0)) + 1;
        return left;
    }

    private Node<K, V> leftRotate(Node<K, V> cur) {
        Node<K, V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0)) + 1;
        right.height = Math.max((right.left != null ? right.left.height : 0), (right.right != null ? right.right.height : 0)) + 1;
        return right;
    }
    

    public static class Node<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }


    public static void main(String[] args) {
        AvlTree<Integer, String> tree = new AvlTree<>();
        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(3, "c");
        tree.remove(2);
        System.out.println(JSON.toJSONString(tree.root));
    }
}
