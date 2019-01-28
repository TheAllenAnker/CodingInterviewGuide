package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.Stack;

public class FindTwoErrorsInBST {
    /**
     * Traverse a binary search tree in in-order will produce a ascending list.
     *
     * @param root
     * @return
     */
    public static TreeNode[] findTwoErrorsInBST(TreeNode root) {
        TreeNode[] errs = new TreeNode[2];
        if (root == null) {
            return errs;
        }

        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && pre.value > root.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = root;
                }
                pre = root;
                root = root.right;
            }
        }

        return errs;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(3);;
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        TreeUtils.printTreeBeautifully(root);
        for (TreeNode node : findTwoErrorsInBST(root)) {
            System.out.println(node.value);
        }
    }
}
