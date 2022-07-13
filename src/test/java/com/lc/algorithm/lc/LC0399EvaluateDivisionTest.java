package com.lc.algorithm.lc;

import com.lc.algorithm.lc.LC0399EvaluateDivision;
import org.junit.Test;

public class LC0399EvaluateDivisionTest {
    @Test
    public void testCalcEquation() throws Exception {
        String[][] equations = new String[][]{{"b","c"},{"a","b"},{"e","f"},{"d","e"},{"b","f"}};
        double[] values = new double[]{2.0,3.0,4.0,5.0,6.0};
        String[][] queries = new String[][]{{"a","d"},{"d","f"},{"a","f"}};
        double[] result = new LC0399EvaluateDivision().calcEquation(equations, values, queries);
        System.out.println("输入：");
        for (int i = 0; i < equations.length; i++) {
            System.out.println(equations[i][0] + " / " + equations[i][0] + " = " + values[i]);
        }
        System.out.println("结果：");
        for (int i = 0; i < queries.length; i++) {
            System.out.println(queries[i][0] + " / " + queries[i][0] + " = " + result[i]);
        }
    }
}