package com.lc.algorithm;

/**
 * 搜索旋转排序数组 -> 二分查找的变种问题
 */
public class LC0033SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                // 说明左边区间是有序的，在在左边区间二分查找
                if (nums[left] <= target && target < nums[mid]) {
                    // 目标值在左边区间
                    right = mid - 1;
                } else {
                    // 目标值在右边区间
                    left = mid + 1;
                }
            } else {
                // 说明右边区间是有序的，在在右边区间二分查找
                if (nums[mid] < target && target <= nums[right]) {
                    // 目标值在右边区间
                    left = mid + 1;
                } else {
                    // 目标值在左边区间
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
