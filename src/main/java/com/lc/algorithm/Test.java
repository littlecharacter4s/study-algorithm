package com.lc.algorithm;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int i = -5;
        System.out.println((i >>> 1));
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
