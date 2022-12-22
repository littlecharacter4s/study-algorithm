package com.lc.algorithm;

import java.util.Arrays;

public class NC0009TopK {
    private static int result = 0;

    public static void main(String[] args) {
        int[] a = new int[]{1, 10, 3, 8, 5, 6, 7, 4, 9, 2};
        a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        quickSort(a, 0, a.length - 1);
        Arrays.stream(a).forEach(System.out::println);
        System.out.println("----------");
        findKth(a, 0, a.length - 1, 7);
        System.out.println(result);
    }

    private static void findKth(int[] a, int s, int e, int k) {
        if (s >= e) {
            return;
        }

        int p = partition(a, s, e);

        if (p == k - 1) {
            result = a[p];
        } else if (p > k - 1) {
            findKth(a, s, p - 1, k);
        } else {
            findKth(a, p + 1, e, k);
        }
    }

    private static void quickSort(int[] a, int s, int e) {
        if (s >= e) {
            return;
        }
        int q = partition(a, s, e);
        quickSort(a, s, q - 1);
        quickSort(a, q + 1, e);
    }

    private static int partition(int[] a, int s, int e) {
        int pivot = a[e];
        int i = s;
        for (int j = s; j < e; j++) {
            if (a[j] < pivot && i != j) {
                swap(a, i, j);
                i = i + 1;
            }
        }
        if (i != e) {
            swap(a, i, e);
        }
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


}
