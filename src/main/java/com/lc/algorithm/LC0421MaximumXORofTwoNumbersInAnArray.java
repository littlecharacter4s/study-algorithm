package com.lc.algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 用{011,101,110}和{101,110,111}来理解，一点点确定最大值（从高位到地位）
 */
public class LC0421MaximumXORofTwoNumbersInAnArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3,10,5,25,26,8};
        int result = new LC0421MaximumXORofTwoNumbersInAnArray().findMaximumXOR(nums);
        System.out.println("result = " + result);
    }

    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(mask & num);
            }
            int temp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public int findMaximumXOREfficient(int[] nums) {
        TrieNode root = new TrieNode();
        // 将数组值从高位到地位构建字典树
        for (int num : nums) {
            TrieNode tpnode = root;
            for (int j = 31; j >= 0; --j) {
                // 注意这两个flag的差别
                int flag = (num & (1 << j)) == 0 ? 0 : 1;
                if (tpnode.children.containsKey(flag)) {
                    tpnode = tpnode.children.get(flag);
                } else {
                    TrieNode newnode = new TrieNode();
                    tpnode.children.put(flag, newnode);
                    tpnode = newnode;
                }
            }
        }
        int max = 0;
        for (int num : nums) {
            // 其实就是用暴力思路求最大异或值，
            // 只不过用字典树(0,1)从高位到地位存储数组元素，计算异或时就不用再遍历一遍数组
            // 利用字典树这种结构将时间复杂度降低为O(n)
            max = Math.max(max, getmax(num, root));
        }
        return max;
    }

    private int getmax(int num, TrieNode root) {
        TrieNode node = root;
        int result = 0;
        for (int j = 31; j >= 0; --j) {
            // 注意这个两个flag的差别
            int flag = (num & (1 << j)) == 0 ? 1 : 0;
            if (node.children.containsKey(flag)) {
                node = node.children.get(flag);
                // 将当前位置1
                result |= (1 << j);
            } else {
                node = node.children.get(1 - flag);
            }
        }
        return result;
    }

    private class TrieNode {
        HashMap<Integer, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
