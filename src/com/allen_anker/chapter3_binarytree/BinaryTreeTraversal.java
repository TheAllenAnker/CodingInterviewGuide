package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.Stack;

public class BinaryTreeTraversal {
    public static void printPreorderRecur(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        if (root.left != null) {
            printPreorderRecur(root.left);
        }
        if (root.right != null) {
            printPreorderRecur(root.right);
        }
    }

    public static void printInorderRecur(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            printInorderRecur(root.left);
        }
        System.out.print(root.value + " ");
        if (root.right != null) {
            printInorderRecur(root.right);
        }
    }

    public static void printPostorderRecur(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            printPostorderRecur(root.left);
        }
        if (root.right != null) {
            printPostorderRecur(root.right);
        }
        System.out.print(root.value + " ");
    }

    public static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            System.out.print(curr.value + " ");
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    public static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                System.out.print(curr.value + " ");
                curr = curr.right;
            }
        }
    }

    public static void printPostorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode curr = root;
        stack1.push(curr);
        while (!stack1.isEmpty()) {
            curr = stack1.pop();
            stack2.push(curr);
            if (curr.left != null) {
                stack1.push(curr.left);
            }
            if (curr.right != null) {
                stack1.push(curr.right);
            }
        }

        curr = stack2.pop();
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = stack2.isEmpty() ? null : stack2.pop();
        }
    }

    public static void printPostorder2(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root, temp = null;
        stack.push(curr);
        while (!stack.isEmpty()) {
            temp = stack.peek();
            if (temp.left != null && temp.left != curr && temp.right != curr) {
                stack.push(temp.left);
            } else if (temp.right != null && curr != temp.right) {
                stack.push(temp.right);
            } else {
                curr = stack.pop();
                System.out.print(curr.value + " ");
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        printPreorderRecur(root);
        System.out.println();
        printInorderRecur(root);
        System.out.println();
        printPostorderRecur(root);
        System.out.println();
        System.out.println("Without using recursion: ");
        printPreorder(root);
        System.out.println();
        printInorder(root);
        System.out.println();
        printPostorder(root);
        System.out.println();
        printPostorder2(root);
        System.out.println();
    }
}
