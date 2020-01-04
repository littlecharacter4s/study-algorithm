package com.lc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.cnblogs.com/zhouthanos/p/3807495.html
 */
public class LC0046FullPermutation {
    List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        sortNums(nums, 0);
        return result;
    }

    private void sortNums(int[] nums, int index) {
        if (index == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int j = index; j < nums.length; j++) {
            if(isUnique(nums, index, j)) {
                swap(nums, index, j);
                sortNums(nums, index + 1);
                // 不还原，for不同次的循环使用的nums不同
                swap(nums, index, j);
            }
        }
    }

    private boolean isUnique(int[] nums, int begin, int end) {
        for(int i = begin; i < end; i++) {
            if (nums[i] == nums[end]) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
