package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class MorrisTraversal {
    /**
     * Space complexity O(1).
     *
     * @param root
     */
    public static void morrisInTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode curr = root;
        TreeNode temp;
        while (curr != null) {
            temp = curr.left;
            if (temp != null) {
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    temp.right = null;
                }
            }
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
    }

    public static void morrisPreTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode curr = root;
        TreeNode temp;
        while (curr != null) {
            System.out.print(curr.value + " ");
            temp = curr.left;
            if (temp != null) {
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    temp.right = curr.right;
                    curr = curr.left;
                    continue;
                } else {
                    temp.right = null;
                }
            }
            curr = curr.right;
        }
    }

    public static void morrisPostTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        morrisInTraversal(root);
        System.out.println();
        morrisPreTraversal(root);
        System.out.println();
        morrisPostTraversal(root);
    }
}
