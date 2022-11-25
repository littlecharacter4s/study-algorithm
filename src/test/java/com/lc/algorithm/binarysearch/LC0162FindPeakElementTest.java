package com.lc.algorithm.binarysearch;

import org.junit.Test;

public class LC0162FindPeakElementTest {
    @Test
    public void testFirstBadVersion() {
        LC0162FindPeakElement findPeakElement = new LC0162FindPeakElement();
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement.findPeakElement(nums));
    }
}