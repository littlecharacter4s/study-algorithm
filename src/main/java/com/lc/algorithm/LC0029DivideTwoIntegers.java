package com.lc.algorithm;

/**
 * 题目：
 * 描述：
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数dividend除以除数divisor得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 分析：
 * 1.防止最小整数除-1导致整数越界
 * 2.将所有操作在负数范围内
 * 链接：https://leetcode.cn/problems/divide-two-integers
 *
 * @author gujixian
 * @since 2022/7/23
 */
public class LC0029DivideTwoIntegers {
    public static void main(String[] args) {
        LC0029DivideTwoIntegers divideTwoIntegers = new LC0029DivideTwoIntegers();
        System.out.println(divideTwoIntegers.divide(2147483647, 3));
    }

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (a == 0) {
            return 0;
        }
        if (b == 1) {
            return a;
        }
        boolean flag = false;
        if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
            flag = true;
        }
        int dd = a > 0 ? -a : a;
        int dr = b > 0 ? -b : b;
        if (dd > dr) {
            return 0;
        }
        int result = 0;
        while (dd <= dr) {
            int value = dr;
            int count = 1;
            // value > Integer.MIN_VALUE  /2 ：防止 value+value 越界
            while (value > Integer.MIN_VALUE / 2 && dd <= value + value) {
                count += count;
                value += value;
            }

            result += count;
            dd -= value;
        }
        return flag ? result : -result;
    }
}
