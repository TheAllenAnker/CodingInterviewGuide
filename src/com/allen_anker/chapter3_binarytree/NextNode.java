package com.allen_anker.chapter3_binarytree;

public class NextNode {
    public static TreeNode getNextNode(TreeNode target) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }

        if (target.parent == null) {
            return target.right;
        }
        if (target.right != null) {
            return getLeftMostNode(target.right);
        }

        TreeNode curr = target;
        TreeNode parent = target.parent;
        while (parent != null && parent.left != curr) {
            curr = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private static TreeNode getLeftMostNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.parent = root;
        root.right = new TreeNode(6);
        root.right.parent = root;
        System.out.println(getNextNode(root).val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int val) {
        this.val = val;
    }
}
