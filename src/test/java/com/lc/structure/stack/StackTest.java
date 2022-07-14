package com.lc.structure.stack;

import org.junit.Test;

public class StackTest {
    LinkedStack linkedStack = new LinkedStack();

    @Test
    public void testLinkedStack() {
        linkedStack.push(1);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(6);
        linkedStack.push(2);
        linkedStack.push(6);
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        System.out.println(linkedStack.max());
    }
}