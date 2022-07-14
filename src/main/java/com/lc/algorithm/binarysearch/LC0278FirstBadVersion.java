package com.lc.algorithm.binarysearch;

import java.util.Random;

/**
 * 题目：第一个错误的版本
 * 描述：
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * 分析：“在一个有序数组中找找>=某个数最左侧的位置”问题
 * 链接：https://leetcode.cn/problems/first-bad-version
 * @author gujixian
 * @since 2022-07-14
 */
public class LC0278FirstBadVersion {
    public int firstBadVersion(int n) {
        // 随机产生一个第一个错误版本
        int firstBadVersion = new Random().nextInt(n) + 1;
        System.out.println("第一个错误版本：" + firstBadVersion);
        int version = 0;
        int left = 1;
        int right = n;
        while(left <= right) {
            int middle = left + ((right - left) >>> 1);
            boolean result = this.isBadVersion(firstBadVersion, middle);
            if (result) {
                version = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return version;
    }

    private boolean isBadVersion(int firstBadVersion, int version) {
        return version >= firstBadVersion;
    }
}
