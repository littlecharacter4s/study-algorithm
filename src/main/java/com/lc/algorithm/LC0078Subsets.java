package com.lc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class LC0078Subsets {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = new LC0078Subsets().subsets(nums);
        System.out.println(JSON.toJSONString(result));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 根据题意，空集也算一个
        result.add(new ArrayList<>());
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                // 重新复制一个list
                List<Integer> list = new ArrayList<>(result.get(i));
                list.add(num);
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> subsetsEfficient(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 幂集的个数(2^nums.length)
        int length = 1 << nums.length;
        /* 位和幂集的一个关系，为1的位表示要取出元素组合，那[1,2,3]举例
         * 0000 空集算一个
         * 0001 取出第一个[1]
         * 0010 取出第二个[2]
         * 0011 取出第一个和第二个[1,2]
         * 0100 取出第三个[3]
         * 0101 取出第一个和第三个[1,3]
         * 0110 取出第二个和第三个[2,3]
         * 0111 取出全部[1,2,3]
         */
        for (int i = 0; i < length; i++) {
            int bit = 1;
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                int data = i & bit;
                if (data > 0) {
                    list.add(num);
                }
                bit <<= 1;
            }
            result.add(list);
        }
        return result;
    }
}
