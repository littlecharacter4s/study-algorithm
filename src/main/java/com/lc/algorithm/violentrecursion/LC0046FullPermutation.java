package com.lc.algorithm.violentrecursion;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 题目：全排列
 * 描述；给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 分析：每个位置都可以选剩余数字的任何一个，一旦选择完毕，剩余数字就把选中的数字移除，传给下一次递归做决定。注意：恢复现场
 * 链接：https://leetcode.cn/problems/permutations/description/
 */
public class LC0046FullPermutation {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new LC0046FullPermutation().permute(new int[]{1,2,3})));
    }

    private List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<Integer> numList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numList.add(num);
        }
        result = new LinkedList<>();
        List<Integer> subResult = new LinkedList<>();
        this.permute(numList, subResult);
        return result;
    }

    private void permute(List<Integer> numList, List<Integer> subResult) {
        if (numList.size() == 0) {
            result.add(new LinkedList<>(subResult));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            int num = numList.get(i);
            subResult.add(num);
            numList.remove(i);
            this.permute(numList, subResult);
            subResult.remove(subResult.size() - 1);
            numList.add(i, num);
        }
    }
}
