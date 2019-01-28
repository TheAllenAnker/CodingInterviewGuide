package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FindTwoErrorsInBST {
    public static TreeNode recoverBST(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        TreeNode[] errs = findTwoErrorsInBST(root);
        if (errs == null || errs.length != 2) {
            throw new IllegalArgumentException("Invalid parameter");
        }
        TreeNode[] parents = getTwoErrsParents(root, errs[0], errs[1]);

        TreeNode err1L = errs[0].left, err1R = errs[0].right;
        TreeNode err2L = errs[1].left, err2R = errs[1].right;
        errs[0].left = err2L;
        errs[0].right = err2R;
        errs[1].left = err1L;
        errs[1].right = err1R;
        if (parents[0] == parents[1]) {
            if (errs[0] == parents[0].left) {
                parents[0].left = errs[1];
                parents[0].right = errs[0];
            } else {
                parents[0].left = errs[0];
                parents[0].right = errs[1];
            }
        } else {
            if (parents[0].left == errs[0]) {
                parents[0].left = errs[1];
            } else {
                parents[0].right = errs[1];
            }
            if (parents[1].left == errs[1]) {
                parents[1].left = errs[0];
            } else {
                parents[1].right = errs[0];
            }
        }

        return root;
    }

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

    private static TreeNode[] getTwoErrsParents(TreeNode root, TreeNode err1, TreeNode err2) {
        // traverse by level and find their parents
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr = root;
        queue.offer(curr);
        TreeNode[] parents = new TreeNode[2];
        while (!queue.isEmpty()) {
            curr = queue.poll();
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            if (left == err1 || right == err1) {
                parents[0] = curr;
            }
            if (left == err2 || right == err2) {
                parents[1] = curr;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }

        return parents;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(3);
        ;
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        TreeUtils.printTreeBeautifully(root);
        for (TreeNode node : findTwoErrorsInBST(root)) {
            System.out.println(node.value);
        }
        recoverBST(root);
        TreeUtils.printTreeBeautifully(root);
    }
}
