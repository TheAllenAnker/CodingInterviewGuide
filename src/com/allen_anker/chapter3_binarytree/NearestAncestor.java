package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class NearestAncestor {
    public static TreeNode getNearestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Invalid parameter(s)");
        }
        if (root == null || root == node1 || root == node2) {
            return root;
        }

        TreeNode left = getNearestCommonAncestor(root.left, node1, node2);
        TreeNode right = getNearestCommonAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node3;
        root.left.left = node4;
        root.left.left.left = node5;
        root.left.left.right = node6;
        TreeUtils.printTreeBeautifully(root);
        System.out.println(root == getNearestCommonAncestor(root, node2, node3));
        System.out.println(node2 == getNearestCommonAncestor(root, node6, node2));
    }
}
