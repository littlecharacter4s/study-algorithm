package com.lc.structure.linear.stack;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedStack<E> {
    private Queue<E> stack;

    public LinkedStack() {
        stack = new LinkedList<>();
    }
    public void push(E element) {
        stack.add(element);
    }

    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.poll();
    }

    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public List<E> getAll() {
        return new LinkedList<>(stack);
    }

    private boolean isEmpty() {
        return stack.isEmpty();
    }


    public static void main(String[] args) {
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
        System.out.println(JSON.toJSONString(linkedStack.getAll()));
    }
}
