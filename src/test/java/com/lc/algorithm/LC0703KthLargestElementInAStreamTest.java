package com.lc.algorithm;

import com.lc.algorithm.lc.LC0703KthLargestElementInAStream;
import org.junit.Test;

public class LC0703KthLargestElementInAStreamTest {
    @Test
    public void testAdd() {
        LC0703KthLargestElementInAStream lc0703 = new LC0703KthLargestElementInAStream(6, new int[]{4,5,8,2});
        System.out.println(lc0703.add(3));
        System.out.println(lc0703.add(5));
        System.out.println(lc0703.add(10));
        System.out.println(lc0703.add(9));
        System.out.println(lc0703.add(4));
    }
}