package com.lc.structure.order;

/**
 * SB 树（SizeBalancedTree）
 *
 * @author gujixian
 * @since 2023/1/2
 */
public class SizeBalancedTree {
    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public int h;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }

    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> l;
        public SBTNode<K, V> r;
        public int size; // 不同的key的数量

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }
}
