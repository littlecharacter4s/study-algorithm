package com.lc.algorithm;

public class N0053MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];
        int start = 0;
        int end = 0;
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] <= nums[i]) {
                start = i;
            }
            sum = Math.max(sum + nums[i], nums[i]);
            if (sum > maxSum) {
                end = i;
            }
            maxSum = Math.max(sum, maxSum);
        }
        System.out.print("最大子序[");
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i]);
            if (i == end) {
                System.out.print("]=");
            } else {
                System.out.print(",");
            }
        }
        System.out.println(maxSum);
        return maxSum;
    }
}
