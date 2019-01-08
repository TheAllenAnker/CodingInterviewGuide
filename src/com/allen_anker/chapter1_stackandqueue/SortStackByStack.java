package com.allen_anker.chapter1_stackandqueue;

import java.util.Stack;

public class SortStackByStack {
    public void sortStackByStack(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        Stack<Integer> helperStack = new Stack<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            while (!helperStack.isEmpty() && curr > helperStack.peek()) {
                stack.push(helperStack.pop());
            }
            helperStack.push(curr);
        }
        while (!helperStack.isEmpty()) {
            stack.push(helperStack.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        new SortStackByStack().sortStackByStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
