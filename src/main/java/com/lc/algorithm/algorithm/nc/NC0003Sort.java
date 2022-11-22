package com.lc.algorithm.algorithm.nc;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Random;

public class NC0003Sort {
    private static final Random RANDOM = new Random();
    
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        // 冒泡排序
        this.bubbleSort(Arrays.copyOf(nums, nums.length));
        // 选择排序
        this.selectionSort(Arrays.copyOf(nums, nums.length));
        // 插入排序
        this.insertionSort(Arrays.copyOf(nums, nums.length));
        // 快速排序
        this.quickSort(Arrays.copyOf(nums, nums.length), 0, nums.length - 1);
        // 归并排序
        this.mergeSort(Arrays.copyOf(nums, nums.length), 0, nums.length - 1);
        // 冒泡排序
        // 打印结果
        for (int num : nums) {
            System.out.print(num + " ");
        }
        return nums;
    }

    /*******************************************************************************************************************
     * 冒泡排序（优化版）
     ******************************************************************************************************************/
    public void bubbleSort(int[] nums) {
        // 0~n-1
        // 0~n-2
        // 0~n-3
        int bottom = nums.length - 1;
        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < bottom; i++) {
                if (nums[i] > nums[i + 1]) {
                    this.swap(nums, i, i + 1);
                    change = true;
                }
            }
            bottom--;
        }
    }

    /*******************************************************************************************************************
     * 选择排序（优化版）（少用）
     ******************************************************************************************************************/
    public void selectionSort(int[] nums) {
        // 0~n-1
        // 1~n-1
        // 2~n-1
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int min = i;
            int max = j;
            for (int k = i; k <= j; k++) {
                if (nums[k] < nums[min]) {
                    min = k;
                }
                if (nums[k] > nums[max]) {
                    max = k;
                }
            }
            if (min == j && max == i) {
                this.swap(nums, min, max);
            } else if (min == j) {
                this.swap(nums, min, i);
                this.swap(nums, j, max);
            } else {
                this.swap(nums, j, max);
                this.swap(nums, min, i);
            }
        }
    }


    /*******************************************************************************************************************
     * 插入排序
     ******************************************************************************************************************/
    public void insertionSort(int[] nums) {
        // 0~1
        // 0~2
        // 0~n-1
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j - 1] > nums[j] ; j--) {
                swap(nums, j - 1, j);
            }
        }
    }


    /*******************************************************************************************************************
     * 快速排序（划分交换排序）：从荷兰国旗问题到快速排序
     * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。
     * 步骤为：
     * 1.从数列中挑出一个元素，称为“基准”（pivot）;
     * 2.重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面，相同的数放在中间。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 3.递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * 注：递归到最底部时，数列的大小是零或一，也就是已经排序好了。这个算法一定会结束，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
     ******************************************************************************************************************/
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] equal = this.partition(nums, left, right);
        this.quickSort(nums, left, equal[0]);
        this.quickSort(nums, equal[1], right);
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


    /*******************************************************************************************************************
     * 归并排序: 文档里的图理解
     ******************************************************************************************************************/
    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        this.mergeSort(nums, left, mid);
        this.mergeSort(nums, mid + 1, right);
        this.merge(nums, left, mid, right);

    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int i = 0;
        while(l <= mid && r <= right) {
            help[i++] = nums[l] <= nums[r] ? nums[l++] : nums[r++];
        }
        while(l <= mid) {
            help[i++] = nums[l++];
        }
        while(r <= right) {
            help[i++] = nums[r++];
        }
        for(int n : help) {
            nums[left++] = n;
        }
    }

    /*******************************************************************************************************************
     * 交换
     ******************************************************************************************************************/
    private void swap(int[] nums, int i, int j) {
        // 判断同一位置不交换很重要，数组同一个位置交换会抹成 0 -> 要想同一个位置也交换就用最常用的 -> 引入一个额外变量
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
