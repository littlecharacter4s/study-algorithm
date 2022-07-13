package com.lc.algorithm.nc;

import com.alibaba.fastjson.JSON;
import com.lc.algorithm.nc.NC0003Sort;
import org.junit.Test;

public class NC0003SortTest {
    @Test
    public void testSort() throws Exception {
        System.out.println("冒泡排序：");
        int[] n = new int[]{3,9,6,5,4,7,8};
        new NC0003Sort().bubbleSort(n);
        System.out.println(JSON.toJSONString(n));

        System.out.println("选择排序：");
        n = new int[]{3,9,6,5,4,7,8};
        new NC0003Sort().selectionSort(n);
        System.out.println(JSON.toJSONString(n));

        System.out.println("插入排序：");
        n = new int[]{9,4,6,8,4,6,7};
        new NC0003Sort().insertionSort(n);
        System.out.println(JSON.toJSONString(n));

        System.out.println("快速排序：");
        n = new int[]{1,3,2,5,4,7,9};
        new NC0003Sort().quickSort(n, 0, n.length - 1);
        System.out.println(JSON.toJSONString(n));
        System.out.println("归并排序：");
        n = new int[]{4,2,5,3,1,6};
        int[] result = new int[n.length];
        new NC0003Sort().mergeSortRecursive(n, result, 0, n.length - 1);
        System.out.println(JSON.toJSONString(result));
        System.out.println(JSON.toJSONString(n));
    }
}