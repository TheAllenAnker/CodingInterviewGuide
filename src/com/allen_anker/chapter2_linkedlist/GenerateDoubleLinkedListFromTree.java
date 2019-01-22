package com.allen_anker.chapter2_linkedlist;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class GenerateDoubleLinkedListFromTree {
    /**
     * Generate a double linked list with the given binary search tree.
     *
     * @param root
     * @return
     */
    public static TreeNode generateList(TreeNode root) {
        if (root == null) {
            return null;
        }
        // it returns the tail of the generated double-linked list
        return generateCore(root, null);
    }

    private static TreeNode generateCore(TreeNode root, TreeNode lastNode) {
        if (root == null) {
            return lastNode;
        }
        // in-order traversal
        TreeNode curr = root;
        if (curr.left != null) {
            lastNode = generateCore(curr.left, lastNode);
        }
        curr.left = lastNode;
        if (lastNode != null) {
            lastNode.right = curr;
        }
        lastNode = curr;
        if (curr.right != null) {
            lastNode = generateCore(curr.right, lastNode);
        }

        return lastNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);
        TreeNode resultList = generateList(root);
        System.out.println(resultList.value);
    }
}
