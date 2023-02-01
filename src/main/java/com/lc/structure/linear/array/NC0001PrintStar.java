package com.lc.structure.linear.array;

/**
 * 题目：转圈打印星号*
 * 描述：
 *  *	*	*	*	*	*	*	*	*	*
 *  									*
 *  	*	*	*	*	*	*	*		*
 *   	*						*		*
 *   	*		*	*	*		*		*
 *   	*		*		*		*		*
 *   	*		*				*		*
 *   	*		*	*	*	*	*		*
 *   	*								*
 *   	*	*	*	*	*	*	*	*	*
 * 分析：宏观调度问题
 *
 * @author gujixian
 * @since 2023/2/1
 */
public class NC0001PrintStar {
    public void printStar(int N) {
        int leftUp = 0;
        int rightDown = N - 1;
        char[][] m = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] = ' ';
            }
        }
        while (leftUp <= rightDown) {
            fill(m, leftUp, rightDown);
            leftUp += 2;
            rightDown -= 2;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fill(char[][] m, int leftUp, int rightDown) {
        for (int col = leftUp; col <= rightDown; col++) {
            m[leftUp][col] = '*';
        }
        for (int row = leftUp + 1; row <= rightDown; row++) {
            m[row][rightDown] = '*';
        }
        for (int col = rightDown - 1; col > leftUp; col--) {
            m[rightDown][col] = '*';
        }
        for (int row = rightDown - 1; row > leftUp + 1; row--) {
            m[row][leftUp + 1] = '*';
        }
    }

    public static void main(String[] args) {
        new NC0001PrintStar().printStar(10);
    }
}
