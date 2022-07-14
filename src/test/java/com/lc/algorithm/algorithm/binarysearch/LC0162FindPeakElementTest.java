package com.lc.algorithm.algorithm.binarysearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author gujixian
 * @since 2022/7/14
 */
public class LC0162FindPeakElementTest {
    @Test
    public void testFirstBadVersion() {
        LC0162FindPeakElement findPeakElement = new LC0162FindPeakElement();
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement.findPeakElement(nums));
    }
}