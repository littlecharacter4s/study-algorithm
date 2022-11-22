package com.lc.algorithm;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] nums = new int[]{5,1,1,2,0,0};
        new Test().sortArray(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        this.quiklySort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quiklySort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equal = this.partition(nums, left, right);
        this.quiklySort(nums, left, equal[0]);
        this.quiklySort(nums, equal[1], right);
    }

    private int[] partition(int[] nums, int left, int right) {
        int pivot = nums[left + new Random().nextInt(right - left + 1)];
        int i = left;
        int l = i - 1;
        int r = right + 1;
        while (i < r) {
            if (nums[i] < pivot) {
                this.swap(nums, ++l, i++);
            } else if (nums[i] > pivot) {
                this.swap(nums, i, --r);
            } else {
                i++;
            }
        }
        return new int[]{l,r};
    }

    private void swap (int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
