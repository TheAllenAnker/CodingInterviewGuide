package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class PrintByLevel {
    public static void printByLevel(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
            System.out.print(curr.value + " ");
        }
        System.out.println();
    }

    public static void printByZigZag(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> helperQueue = new LinkedList<>();
        queue.offer(root);
        boolean fromLeft = true;
        while (!queue.isEmpty()) {
            TreeNode curr = fromLeft ? queue.poll() : queue.pollLast();
            System.out.print(curr.value + " ");
            if (fromLeft) {
                if (curr.left != null) {
                    helperQueue.offer(curr.left);
                }
                if (curr.right != null) {
                    helperQueue.offer(curr.right);
                }
            } else {
                if (curr.right != null) {
                    helperQueue.offerFirst(curr.right);
                }
                if (curr.left != null) {
                    helperQueue.offerFirst(curr.left);
                }
            }
            if (queue.isEmpty()) {
                queue = helperQueue;
                helperQueue = new LinkedList<>();
                fromLeft = !fromLeft;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        printByLevel(root);
        printByZigZag(root);
    }
}
