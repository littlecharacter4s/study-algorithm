package com.lc.algorithm.algorithm.nc;

import com.alibaba.fastjson.JSON;

import java.util.Random;

public class NC0003Sort {
    /**
     * 冒泡排序（优化版）
     *
     * @param array 待排序数组
     */
    public void bubbleSort(int[] array) {
        // 0~n-1
        // 0~n-2
        // 0~n-3
        int bubble = array.length - 1;
        boolean change;
        do {
            change = false;
            for (int i = 0; i < bubble; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    change = true;
                }
            }
            bubble--;
        } while (change);
    }

    /**
     * 选择排序（优化版）
     *
     * @param array 待排序数组
     */
    public void selectionSort(int[] array) {
        // 0~n-1
        // 1~n-1
        // 2~n-1
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int min = i;
            int max = j;
            for (int k = i; k <= j; k++) {
                if (array[k] < array[min]) {
                    min = k;
                }
                if (array[k] > array[max]) {
                    max = k;
                }
            }
            if (min == j && max == i) {
                int temp = array[min];
                array[min] = array[max];
                array[max] = temp;
            } else if (min == j) {
                int minTemp = array[i];
                array[i] = array[min];
                array[min] = minTemp;

                int maxTemp = array[j];
                array[j] = array[max];
                array[max] = maxTemp;
            } else {
                int maxTemp = array[j];
                array[j] = array[max];
                array[max] = maxTemp;

                int minTemp = array[i];
                array[i] = array[min];
                array[min] = minTemp;
            }
        }
    }


    /**
     * 插入排序
     *
     * @param array 待排序数组
     */
    public void insertionSort(int[] array) {
        // 0~1
        // 0~2
        // 0~n-1
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[j + 1] ; j--) {
                swap(array, j, j + 1);
            }
        }
    }


    /**
     * 快速排序（划分交换排序）：从荷兰国旗问题到快速排序
     * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。
     * 步骤为：
     * 1.从数列中挑出一个元素，称为“基准”（pivot）;
     * 2.重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面，相同的数放在中间。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 3.递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * 注：递归到最底部时，数列的大小是零或一，也就是已经排序好了。这个算法一定会结束，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
     *
     * @param array
     * @param head
     * @param tail
     */
    private static final Random RANDOM = new Random();
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        this.quiklySort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quiklySort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equal = this.partition(nums, left, right);
        this.quiklySort(nums, left, equal[0]);
        this.quiklySort(nums, equal[1], right);
    }

    private int[] partition(int[] nums, int left, int right) {
        int pivot = nums[left + RANDOM.nextInt(right - left + 1)];
        int i = left;
        int l = i - 1;
        int r = right + 1;
        while (i < r) {
            if (nums[i] < pivot) {
                this.swap(nums, ++l, i++);
            } else if (nums[i] > pivot) {
                this.swap(nums, i, --r);
            } else {
                i++;
            }
        }
        return new int[]{l,i};
    }

    /**
     * 归并排序（递归版）: 文档里的图理解
     *
     * @param arr
     * @param result
     * @param start
     * @param end
     */
    public void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start;
        int mid = (len >>> 1) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        // 开始归并
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        // 结果补全
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        // 结果补全
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        // 每次归并保证当前部分有序，因为下次归并在“开始归并处”会用到
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
        System.out.println(JSON.toJSONString(arr));
    }

    /**
     * 归并排序（迭代版）
     *
     * @param arr
     */
    public void mergeSortIteration(int[] arr) {
        int[] orderedArr = new int[arr.length];
        for (int i = 2; i < arr.length * 2; i *= 2) {
            for (int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid) {
                    orderedArr[start++] = arr[l++];
                }
                while (m <= right) {
                    orderedArr[start++] = arr[m++];
                }
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
