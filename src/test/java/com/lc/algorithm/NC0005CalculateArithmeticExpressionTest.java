package com.lc.algorithm;

import com.lc.algorithm.NC0005CalculateArithmeticExpression;
import org.junit.Test;

public class NC0005CalculateArithmeticExpressionTest {
    @Test
    public void calculate() throws Exception {
        String expression = "9+(3-1)*3+10/2";
        float result = new NC0005CalculateArithmeticExpression().calculate(expression);
        System.out.println("9+(3-1)*3+10/2=" + result);
    }
}