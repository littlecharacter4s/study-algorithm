package com.lc.algorithm;

import com.lc.structure.linear.stack.ArrayStack;

/**
 * 计算四则运算表达式 - 栈的运用
 * 第一步：将中缀表达转换成后缀表达式；
 * 1.当当前字符为数字时，直接输出；
 * 2.当当前字符为"("时，将其压栈；
 * 3.当当前字符为")"时，则弹出堆栈中最上的"("之前的所有运算符并输出，然后删除堆栈中的"("；
 * 4.当当前字符为运算符时，则依次弹出堆栈中优先级大于等于当前运算符的(到"("之前为止)运算符，输出，再将当前运算符压栈；
 * 5.最后弹出所有栈中的内容输出。
 * 第二步：计算后缀表达式。
 * 从左到右遍历表达式的每个数字和符号，遇到数字就进栈，遇到是符号，就将处于栈顶的两个数字出栈，进行计算，然后计算结果进栈，一直到最终获得结果。
 */
public class NC0002CalculateArithmeticExpression {
    public float calculate(String expression) {
        float result = 0.0F;
        expression = this.convert(expression);
        // 计算：从左到右遍历表达式的每个数字和符号，遇到数字就进栈，遇到是符号，就将处于栈顶的两个数字出栈，进行计算，然后计算结果进栈，一直到最终获得结果。
        return result;
    }

    /**
     * 将中缀表达转换成后缀表达式
     * 1.当当前字符为数字时，直接输出；
     * 2.当当前字符为"("时，将其压栈；
     * 3.当当前字符为")"时，则弹出堆栈中最上的"("之前的所有运算符并输出，然后删除堆栈中的"("；
     * 4.当当前字符为运算符时，则依次弹出堆栈中优先级大于等于当前运算符的(到"("之前为止)运算符，输出，再将当前运算符压栈；
     * 5.最后弹出所有栈中的内容输出。
     * @param expression
     * @return
     */
    private String convert(String expression) {
        StringBuilder sb = new StringBuilder();
        char[] characters = expression.toCharArray();
        ArrayStack<Character> arrayStack = new ArrayStack<>();
        for (int i = 0; i < characters.length; i++) {
            if ('0' <= characters[i] && characters[i] <= '9') {
                sb.append(characters[i]);
            }
            if (characters[i] == '(') {
                arrayStack.push(characters[i]);
            }
            // 未完待续...
        }
        return sb.toString();
    }
}
