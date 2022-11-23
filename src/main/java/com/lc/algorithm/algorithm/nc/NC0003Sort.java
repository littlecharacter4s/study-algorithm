package com.lc.algorithm.algorithm.nc;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class NC0003Sort {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        System.out.println("排序结果：" + JSON.toJSONString(new NC0003Sort().sortArray(new int[]{7,1,2,3,5,5,0,7,9,1,8,9,4,0,3,2,6,4,8,6})));
    }
    
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        System.out.println("原始数组：" + JSON.toJSONString(nums));
        
        int[] nums4Sort;
        
        // 冒泡排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.bubbleSort(nums4Sort);
        System.out.println("冒泡排序：" + JSON.toJSONString(nums4Sort));
        
        // 选择排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.selectionSort(nums4Sort);
        System.out.println("选择排序：" + JSON.toJSONString(nums4Sort));
        
        // 插入排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.insertionSort(nums4Sort);
        System.out.println("插入排序：" + JSON.toJSONString(nums4Sort));
        
        // 快速排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.quickSort(nums4Sort, 0, nums.length - 1);
        System.out.println("快速排序：" + JSON.toJSONString(nums4Sort));
        
        // 归并排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.mergeSort(nums4Sort, 0, nums.length - 1);
        System.out.println("归并排序：" + JSON.toJSONString(nums4Sort));
        
        // 堆排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.heapSort(nums4Sort);
        System.out.println("堆排序V1：" + JSON.toJSONString(nums4Sort));
        
        // 堆排序
        nums4Sort = Arrays.copyOf(nums, nums.length);
        this.heapSortByJava(nums4Sort);
        System.out.println("堆排序V2：" + JSON.toJSONString(nums4Sort));
        
        // 返回结果
        return nums4Sort;
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
     * 堆排序
     ******************************************************************************************************************/
    public void heapSort(int[] nums) {
        this.heapify(nums); // O(N)
        int size = nums.length;
        // O(N*logN)
        for (int i = nums.length - 1; i > 0; i--) { // O(N)
            int x = nums[i];
            nums[i] = nums[0];
            size --;
            this.siftDownV2(nums, size, 0, x); // O(logN)
        }
    }

    private void heapify(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            this.siftDownV2(nums, nums.length, i, nums[i]);
        }
    }

    // 自上而下调整节点 - 小顶堆 - 降序排序
    private void siftDown(int[] nums, int size, int i, int x) {
        // 第一个无子节点的节点
        int left = (i << 1) + 1;
        while (left < size) {
            int right = left + 1;
            // 最后一个有子节点的节点可能没有右节点，这里处理一下防止越界
            right = right < size ? right : left;
            int min = nums[left] > nums[right] ? right : left;
            if (x <= nums[min]) {
                break;
            }
            nums[i] = nums[min];
            i = min;
            left = (i << 1) + 1;
        }
        nums[i] = x;
    }

    // 自上而下调整节点 - 大顶堆 - 升序排序
    private void siftDownV2(int[] nums, int size, int i, int x) {
        // 第一个无子节点的节点
        int left = (i << 1) + 1;
        while (left < size) {
            int right = left + 1;
            // 最后一个有子节点的节点可能没有右节点，这里处理一下防止越界
            right = right < size ? right : left;
            int max = nums[left] < nums[right] ? right : left;
            if (x >= nums[max]) {
                break;
            }
            nums[i] = nums[max];
            i = max;
            left = (i << 1) + 1;
        }
        nums[i] = x;
    }


    /*******************************************************************************************************************
     * 堆排序
     ******************************************************************************************************************/
    public void heapSortByJava(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((i1, i2) -> {
            // 因为 PriorityQueue 是小顶堆，
            // 所以，使用 i1 - i2 判断表示升序，使用 i2 - i1 表示降序
            // 结论，不必纠结，跑一下就知道了
            i1 = Optional.ofNullable(i1).orElse(0);
            i2 = Optional.ofNullable(i2).orElse(0);
            return i2 - i1;
        });
        for (int num : nums) {
            heap.offer(num);
        }
        int index = 0;
        while (!heap.isEmpty()) {
            nums[index++] = heap.poll();
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
