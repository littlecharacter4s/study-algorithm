package com.lc.algorithm;

import org.junit.Test;

public class NC0002CalculateArithmeticExpressionTest {
    @Test
    public void calculate() throws Exception {
        String expression = "9+(3-1)*3+10/2";
        float result = new NC0002CalculateArithmeticExpression().calculate(expression);
        System.out.println("9+(3-1)*3+10/2=" + result);
    }
}