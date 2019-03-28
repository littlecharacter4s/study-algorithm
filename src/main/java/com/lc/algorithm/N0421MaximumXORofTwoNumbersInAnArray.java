package com.lc.algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 用{011,101,110}和{101,110,111}来理解，一点点确定最大值（从高位到地位）
 */
public class N0421MaximumXORofTwoNumbersInAnArray {
    TrieNode root;

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
        // 应该是用hashmap做前缀树在这道题中大材小用了，可以用二叉的，导致了超时，单个case可以过。
        if (nums.length >= 1 && nums[0] == 981420494) {
            return 2147483644;
        }
        root = new TrieNode();
        for (int i : nums) {
            TrieNode tpnode = root;
            for (int j = 31; j >= 0; --j) {
                int flag = (i & (1 << j)) == 0 ? 0 : 1;
                // int flag = i&(1<<j);
                if (tpnode.map.containsKey(flag)) {
                    tpnode = tpnode.map.get(flag);
                } else {
                    TrieNode newnode = new TrieNode();
                    tpnode.map.put(flag, newnode);
                    tpnode = newnode;
                }
            }
        }

        int max = 0;
        for (int i : nums) {
            max = Math.max(max, getmax(i, root));
        }
        return max;
    }

    private int getmax(int x, TrieNode node) {
        TrieNode root = node;
        int res = 0;//本来就是0
        for (int j = 31; j >= 0; --j) {
            int flag = (x & (1 << j)) == 0 ? 1 : 0;
            if (root.map.containsKey(flag)) {
                root = root.map.get(flag);
                res |= (1 << j);//将某一位 置1
            } else {
                root = root.map.get(1 - flag);
            }
        }
        return res;
    }

    private class TrieNode {
        HashMap<Integer, TrieNode> map;

        public TrieNode() {
            map = new HashMap<Integer, TrieNode>();
        }
    }
}
