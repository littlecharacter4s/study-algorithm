package com.lc.structure.linear.stack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ArrayStack<E> {
    private ArrayList<E> stack;

    public ArrayStack() {
        stack = new ArrayList<>();
    }

    public void push(E element) {
        stack.add(element);
    }

    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public List<E> getAll() {
        return new ArrayList<>(stack);
    }

    private boolean isEmpty() {
        return stack.isEmpty();
    }


    public static void main(String[] args) {
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
        System.out.println(JSON.toJSONString(arrayStack.getAll()));
    }
}
