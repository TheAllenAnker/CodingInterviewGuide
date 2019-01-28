package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

public class TreeFullMatch {
    /**
     * The tree1 must have a sub-tree that has exactly the same structure with tree2.
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isTree2HasTree1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }

        return isTreeHasTreeCore(root1, root2) || isTree2HasTree1(root1.left, root2) || isTree2HasTree1(root1.right, root2);
    }

    private static boolean isTreeHasTreeCore(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.value != root2.value) {
            return false;
        }

        return isTreeHasTreeCore(root1.left, root2.left) && isTreeHasTreeCore(root1.right, root2.right);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        root.left.left.left = new TreeNode(8);
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        TreeUtils.printTreeBeautifully(root);
        TreeUtils.printTreeBeautifully(root2);
        System.out.println(isTree2HasTree1(root, root2));
    }
}
