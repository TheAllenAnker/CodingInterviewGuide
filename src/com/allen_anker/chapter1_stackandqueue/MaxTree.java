package com.allen_anker.chapter1_stackandqueue;

import java.util.HashMap;
import java.util.Stack;

public class MaxTree {
    /**
     * Construct the max tree of the given arr.
     * The arr has no duplicate elements.
     *
     * @param arr
     * @return
     */
    public TreeNode getMaxTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int length = arr.length;
        TreeNode[] nodes = new TreeNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new TreeNode(arr[i]);
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> lBigMap = new HashMap<>();
        HashMap<TreeNode, TreeNode> rBigMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            populateBigMap(nodes, stack, lBigMap, i);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);
        }
        for (int i = length - 1; i >= 0; i--) {
            populateBigMap(nodes, stack, rBigMap, i);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }

        TreeNode head = null;
        for (int i = 0; i < length; i++) {
            TreeNode currNode = nodes[i];
            TreeNode leftBig = lBigMap.get(currNode);
            TreeNode rightBig = rBigMap.get(currNode);
            if (leftBig == null && rightBig == null) {
                head = currNode;
            } else if (leftBig == null) {
                if (rightBig.left == null) {
                    rightBig.left = currNode;
                } else {
                    rightBig.right = currNode;
                }
            } else if (rightBig == null) {
                if (leftBig.left == null) {
                    leftBig.left = currNode;
                } else {
                    leftBig.right = currNode;
                }
            } else {
                TreeNode parent = leftBig.value < rightBig.value ? leftBig : rightBig;
                if (parent.left == null) {
                    parent.left = currNode;
                } else {
                    parent.right = currNode;
                }
            }
        }

        return head;
    }

    private void populateBigMap(TreeNode[] nodes, Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> bigMap, int i) {
        TreeNode currNode = nodes[i];
        while (!stack.isEmpty() && stack.peek().value < currNode.value) {
            popStackSetMap(stack, bigMap);
        }
        stack.push(currNode);
    }

    private void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> hashMap) {
        TreeNode curr = stack.pop();
        if (stack.isEmpty()) {
            hashMap.put(curr, null);
        } else {
            hashMap.put(curr, stack.peek());
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 5, 1, 2};
        TreeNode head = new MaxTree().getMaxTree(arr);
        System.out.println(head.value);
    }
}
