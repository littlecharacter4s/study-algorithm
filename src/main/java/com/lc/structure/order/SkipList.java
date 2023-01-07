package com.lc.structure.order;


import java.util.ArrayList;

/**
 * 跳表
 *
 * @author gujixian
 * @since 2023/1/2
 */
public class SkipList<K extends Comparable<K>, V> {
    // 每次 roll 层数 +1 的概率
    // -> Redis 的概率为 0.25，但是其 head 节点的层数固定 32 层
    private static final double PROBABILITY = 0.5;

    private Node<K, V> head;
    private int size;
    private int maxLevel;


    public SkipList() {
        head = new Node<>(null, null);
        head.levelNodes.add(null);
        size = 0;
        maxLevel = 0;
    }


    public V put(K key, V value) {
        if (key == null) {
            return null;
        }
        // 找到 0 层上，最右一个小于指定 key 的 Node -> >key
        Node<K, V> less = getLastSmallerNodeInTree(key);
        Node<K, V> find = less.levelNodes.get(0);
        // key 存在
        if (find != null && find.equalTo(key)) {
            V oldValue = find.value;
            find.value = value;
            return oldValue;
        }
        // key 不存在
        size++;
        int newLevel = 0;
        // roll 层数
        while (Math.random() < PROBABILITY) {
            newLevel++;
        }
        // roll 出的层数大于当前最大层数 -> 更新最大层数，并初始化 head 节点相应的层
        while (newLevel > maxLevel) {
            head.levelNodes.add(null);
            maxLevel++;
        }
        Node<K, V> newNode = new Node<>(key, value);
        // 新增节点，并初始化层
        for (int i = 0; i <= newLevel; i++) {
            newNode.levelNodes.add(null);
        }
        // roll 出的 level 既可能大于当前最大 maxLevel，也可能小于等于当前最大 maxLevel -> 下面的 while 是最简洁的写法
        int level = maxLevel;
        Node<K, V> node = head;
        while (level >= 0) {
            // 找到位置：获取某层中 key 小于指定 key 的最大的节点
            node = getLastSmallerNodeInLevel(key, node, level);
            // 插入节点
            if (level <= newLevel) {
                newNode.levelNodes.set(level, node.levelNodes.get(level));
                node.levelNodes.set(level, newNode);
            }
            level--;
        }
        return value;
    }

    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        size--;
        V value = null;
        int level = maxLevel;
        Node<K, V> node = head;
        while (level >= 0) {
            // 找到位置：获取某层中 key 小于指定 key 的最大的节点
            node = getLastSmallerNodeInLevel(key, node, level);
            Node<K, V> find = node.levelNodes.get(level);
            // 删除节点 <- 如果存在
            if (find != null && find.equalTo(key)) {
                value = find.value;
                node.levelNodes.set(level, find.levelNodes.get(level));
            }
            // 某层只有一个节点了，即 head -> 删除层
            // -> 其实都指向 null 了，也可以不删，浪费不了多少空间
            // -> Redis 的 head 节点还固定 32 层呢
            if (level != 0 && node == head && node.levelNodes.get(level) == null) {
                head.levelNodes.remove(level);
                maxLevel--;
            }
            level--;
        }
        return value;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        Node<K, V> less = getLastSmallerNodeInTree(key);
        Node<K, V> next = less.levelNodes.get(0);
        return next != null && next.equalTo(key);
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> less = getLastSmallerNodeInTree(key);
        Node<K, V> next = less.levelNodes.get(0);
        return next != null && next.equalTo(key) ? next.value : null;
    }

    public K firstKey() {
        return head.levelNodes.get(0) != null ? head.levelNodes.get(0).key : null;
    }

    public K lastKey() {
        int level = maxLevel;
        Node<K, V> curr = head;
        while (level >= 0) {
            Node<K, V> next = curr.levelNodes.get(level);
            while (next != null) {
                curr = next;
                next = curr.levelNodes.get(level);
            }
            level--;
        }
        return curr.key;
    }

    // 天花板：获取大于等于指定 key 的最小 key
    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> smaller = getLastSmallerNodeInTree(key);
        Node<K, V> next = smaller.levelNodes.get(0);
        return next != null ? next.key : null;
    }

    // 地板：获取小于等于指定 key 的最大 key
    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> smaller = getLastSmallerNodeInTree(key);
        Node<K, V> next = smaller.levelNodes.get(0);
        return next != null && next.lessThan(key) ? next.key : smaller.key;
    }

    public int size() {
        return size;
    }


    // 获取整棵树中（0 层上） key 小于指定 key 的最大的节点（Last Smaller Node）
    private Node<K, V> getLastSmallerNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        int level = maxLevel;
        Node<K, V> node = head;
        while (level >= 0) {
            // node  level -> level-1
            node = getLastSmallerNodeInLevel(key, node, level--);
        }
        return node;
    }

    // 获取某层中 key 小于指定 key 的最大的节点（Last Smaller Node）
    private Node<K, V> getLastSmallerNodeInLevel(K key, Node<K, V> curr, int level) {
        Node<K, V> next = curr.levelNodes.get(level);
        while (next != null && next.lessThan(key)) {
            curr = next;
            next = curr.levelNodes.get(level);
        }
        return curr;
    }


    private static class Node<K extends Comparable<K>, V> {
        private final K key;
        private V value;
        // 可以使用数组，例如：Redis 的跳表最大高度限制 32
        private ArrayList<Node<K, V>> levelNodes = new ArrayList<>();

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // node里面的 key 是否比 otherKey 小，是 true，不是 false
        public boolean lessThan(K otherKey) {
            //  otherKey == null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        // node里面的 key 和 otherKey 是否相等，是 true，不是 false
        public boolean equalTo(K otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }


    public static void main(String[] args) {
        SkipList<String, String> test = new SkipList<>();
        test.put("A", "10");
        printAll(test);
        System.out.println("-----------------------------------------------------------------------------------------");
        test.remove("A");
        printAll(test);
        System.out.println("-----------------------------------------------------------------------------------------");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("-----------------------------------------------------------------------------------------");
        test.remove("D");
        printAll(test);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
    }

    // for test
    public static void printAll(SkipList<String, String> test) {
        for (int i = test.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            Node<String, String> cur = test.head;
            while (cur.levelNodes.get(i) != null) {
                Node<String, String> next = cur.levelNodes.get(i);
                System.out.print("(" + next.key + "," + next.value + "," + next.levelNodes.size() + ") ");
                cur = next;
            }
            System.out.println();
        }
    }
}
