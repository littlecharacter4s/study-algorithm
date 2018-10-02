package com.lc.algorithm;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class NaaacSortTest {
    @Test
    public void testSort() throws Exception {
        System.out.println("冒泡排序：");
        int[] n = new int[]{3,9,6,5,4,7,8};
        new NaaacSort().bubbleSort(n);
        System.out.println(JSON.toJSONString(n));

        System.out.println("插入排序：");
        n = new int[]{9,4,6,8,4,6,7};
        new NaaacSort().insertionSort(n);
        System.out.println(JSON.toJSONString(n));

        System.out.println("快速排序：");
        n = new int[]{1,2,3,5,4,7,9};
        new NaaacSort().quickSort(n, 0, n.length - 1);
        System.out.println(JSON.toJSONString(n));
        System.out.println("归并排序：");
        n = new int[]{4,2,5,3,1,6};
        int[] result = new int[n.length];
        new NaaacSort().mergeSortRecursive(n, result, 0, n.length - 1);
        System.out.println(JSON.toJSONString(n));
    }
}