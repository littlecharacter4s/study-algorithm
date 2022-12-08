package com.lc.algorithm.znc;

import java.util.*;

/**
 * 动态规划-最短路径（https://time.geekbang.org/column/article/73188）
 * S-A=1
 * S-B=1
 * S-C=1
 * <p>
 * A-E=1
 * A-F=1
 * B-D=1
 * B-F=1
 * C-D=1
 * C-E=1
 * <p>
 * D-T=1
 * E-T=1
 * F-T=1
 * <p>
 * f(T) = min(f(D) + DT, f(E) + ET, f(F) + FT);
 *
 * 注意：这个算法是不严谨的，比如有跨层连通的
 */
public class NC0011DynamicProgramming {
    private static final int LEN = 3;
    // 乘次List<每层的节点<节点名称, 到达本节点的前置节点<节点名称, 两个节点的距离>>>
    private static List<Map<Integer, Map<Integer, Integer>>> nodeList = new ArrayList<>(LEN);

    public static void main(String[] args) {
        build();

        int i = nodeList.size() - 1;
        int j = nodeList.get(i).size() - 1;

        System.out.println(calMinDistance(i, j));
    }

    private static void build() {
        Map<Integer, Integer> preNodeMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> nodeMap = new HashMap<>();
        // 第1次决策
        nodeMap = new HashMap<>();
        preNodeMap.put(0, 1);
        nodeMap.put(0, preNodeMap);

        preNodeMap = new HashMap<>();
        preNodeMap.put(0, 2);
        nodeMap.put(1, preNodeMap);

        preNodeMap = new HashMap<>();
        preNodeMap.put(0, 3);
        nodeMap.put(2, preNodeMap);
        nodeList.add(nodeMap);
        // 第2次决策
        nodeMap = new HashMap<>();
        preNodeMap = new HashMap<>();
        preNodeMap.put(1, 2);
        preNodeMap.put(2, 3);
        nodeMap.put(0, preNodeMap);

        preNodeMap = new HashMap<>();
        preNodeMap.put(0, 4);
        preNodeMap.put(2, 2);
        nodeMap.put(1, preNodeMap);

        preNodeMap = new HashMap<>();
        preNodeMap.put(0, 5);
        preNodeMap.put(1, 6);
        nodeMap.put(2, preNodeMap);
        nodeList.add(nodeMap);
        // 第3次决策
        nodeMap = new HashMap<>();
        preNodeMap = new HashMap<>();
        preNodeMap.put(0, 2);
        preNodeMap.put(1, 4);
        preNodeMap.put(2, 1);
        nodeMap.put(0, preNodeMap);
        nodeList.add(nodeMap);
    }

    private static int calMinDistance(int i, int j) {
        if (i == 0) {
            Map<Integer, Map<Integer, Integer>> nodeMap = nodeList.get(i);
            Map<Integer, Integer> preNodeMap = nodeMap.get(j);
            return preNodeMap.get(0);
        }

        Map<Integer, Map<Integer, Integer>> nodeMap = nodeList.get(i);
        Map<Integer, Integer> preNodeMap = nodeMap.get(j);
        Set<Integer> keySet = preNodeMap.keySet();

        int min = Integer.MAX_VALUE;

        for (int key : keySet) {
            int distance = calMinDistance(i-1, key) + preNodeMap.get(key);
            min = distance < min ? distance : min;
        }

        return min;
    }
}
