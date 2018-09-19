package com.lc.algorithm;

import org.junit.Test;

public class KthLargestElementInAnArray215Test {
    @Test
    public void testFindKthLargestElement() throws Exception {
        int[] elements = new int[]{3,2,3,1,2,4,5,5,6};
        KthLargestElementInAnArray215 kth = new KthLargestElementInAnArray215();
        int result = kth.findKthLargestElement(elements, 4);
        System.out.println(result);
    }
}