package com.lc.algorithm;

/**
 *
 * 思路：利用动态规划做题。
 * 只遍历数组一遍，当从头到尾部遍历数组A， 遇到一个数有两种选择 （1）加入之前subArray （2）自己另起一个subArray
 * 设状态S[i], 表示以A[i]结尾的最大连续子序列和，状态转移方程如下:
 * S[i] = max(S[i-1] + A[i],A[i])
 * 从状态转移方程上S[i]只与S[i-1]有关，与其他都无关，因此可以用一个变量来记住前一个的最大连续数组和就可以了。
 * 分析：时间复杂度：O(n) 空间复杂度：O(1)
 * 步骤：
 * 1、遍历array，（之前的sum + 这个数字） 和 （这个数字） 比大小，
 * 2、如果（这个数字）比 （之前的sum + 这个数字） 大的话，那么说明不需要再继续加了，直接从这个数字，开始继续，因为它自己已经比之前的sum都大了。
 * 3、否则就继续加下去。
 */
public class LC0053MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int result = new LC0053MaximumSubarray().maxSubArray(nums);
        System.out.println("结果:" + result);
    }

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
