package com.lc.algorithm.binarysearch;

import org.junit.Test;

public class LC0704BinarySearchTest {
    @Test
    public void testSearch() {
        LC0704BinarySearch binarySearch = new LC0704BinarySearch();
        int[] nums = {-1,0,3,5,9,12};
        int target = 5;
        System.out.println(binarySearch.search(nums, target));
    }
}