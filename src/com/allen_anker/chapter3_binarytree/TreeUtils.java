package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class TreeUtils {
    /**
     * Print the given binary tree horizontally.
     *
     * @param root
     */
    public static void printTreeBeautifully(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println("Binary Tree: ");
        printInOrder(root, 0, "H", 18);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }

        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    /**
     * Get a string that is composed by the required number of space.
     *
     * @param num
     * @return
     */
    public static String getSpace(int num) {
        String space = " ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(space);
        }

        return sb.toString();
    }

    public static TreeNode getSimpleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        printTreeBeautifully(root);
    }
}
