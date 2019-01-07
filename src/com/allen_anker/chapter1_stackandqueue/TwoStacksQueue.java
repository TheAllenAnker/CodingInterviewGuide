package com.allen_anker.chapter1_stackandqueue;

import java.util.Stack;

public class TwoStacksQueue<T> {
    private Stack<T> dataStack;
    private Stack<T> helperStack;

    public TwoStacksQueue() {
        dataStack = new Stack<>();
        helperStack = new Stack<>();
    }

    public void add(T element) {
        dataStack.push(element);
    }

    public T poll() {
        if (helperStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                helperStack.push(dataStack.pop());
            }
        }

        return helperStack.pop();
    }

    public T peek() {
        if (helperStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                helperStack.push(dataStack.pop());
            }
        }

        return helperStack.peek();
    }

    public static void main(String[] args) {
        TwoStacksQueue<Integer> twoStacksQueue = new TwoStacksQueue<>();
        twoStacksQueue.add(1);
        twoStacksQueue.add(2);
        twoStacksQueue.add(3);
        System.out.println(twoStacksQueue.peek());
        System.out.println(twoStacksQueue.poll());
    }
}
