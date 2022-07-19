package com.lc.algorithm.structure.stack;

import com.alibaba.fastjson.JSON;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

public class StackTest {
    @Test
    public void testArrayStack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        arrayStack.push(1);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(6);
        arrayStack.push(2);
        arrayStack.push(6);
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        System.out.println(JSON.toJSONString(arrayStack.get()));
    }

    @Test
    public void testLinkedStack() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(1);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(6);
        linkedStack.push(2);
        linkedStack.push(6);
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        System.out.println(JSON.toJSONString(linkedStack.get()));
    }
}