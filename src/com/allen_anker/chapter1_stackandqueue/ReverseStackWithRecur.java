package com.allen_anker.chapter1_stackandqueue;

import java.util.Stack;

public class ReverseStackWithRecur {
    public static <T> void reverseStack(Stack<T> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        T currLast = getAndRemoveLastInStack(stack);
        reverseStack(stack);
        stack.push(currLast);
    }

    private static <T> T getAndRemoveLastInStack(Stack<T> stack) {
        T result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            T last = getAndRemoveLastInStack(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverseStack(stack);
        System.out.println(stack.pop());
    }
}
