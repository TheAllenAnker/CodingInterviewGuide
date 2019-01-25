package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class TreeUtils {
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
}
