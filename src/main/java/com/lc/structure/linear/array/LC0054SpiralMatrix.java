package com.lc.structure.linear.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：螺旋矩阵（顺时针打印矩阵）
 * 描述：给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 分析：Coding游戏
 * 1.找规律
 * 2.找终止条件while(true)
 * 链接：https://leetcode.cn/problems/spiral-matrix
 * @author gujixian
 * @since 2022/7/20
 */
public class LC0054SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        List<Integer> result = new ArrayList((right + 1) * (bottom + 1));
        while (true) {
            // left -> right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            if (++top > bottom) break;
            // top -> bottom
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            if (--right < left) break;
            // right -> left
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            if (--bottom < top) break;
            // bottom -> top
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return result;
    }
}
