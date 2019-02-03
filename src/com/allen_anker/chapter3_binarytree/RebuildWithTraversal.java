package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.HashMap;

/**
 * Assuming all nodes in the tree have unique values, rebuild the tree with two traversals.
 */
public class RebuildWithTraversal {
    public static TreeNode rebuildWithPreAndInOrderTraversal(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            indexMap.put(in[i], i);
        }

        return preAndIn(pre, 0, pre.length - 1, in, 0, in.length - 1, indexMap);
    }

    private static TreeNode preAndIn(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd,
                                     HashMap<Integer, Integer> indexMap) {
        if (preStart > preEnd) {
            return null;
        }

        int value = pre[preStart];
        TreeNode head = new TreeNode(value);
        int index = indexMap.get(value);
        head.left = preAndIn(pre, preStart + 1, preStart + index - inStart, in, inStart, index - 1, indexMap);
        head.right = preAndIn(pre, preStart + index - inStart + 1, preEnd, in, index + 1, inEnd, indexMap);

        return head;
    }

    public static TreeNode rebuildWithInAndPostOrderTraversal(int[] in, int[] post) {
        if (in == null || post == null || in.length != post.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        return null;
    }

    public static TreeNode rebuildWithPreAndPostOrderTraversal(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length != post.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        return null;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        int[] post = {4, 8, 9, 5, 2, 6, 7, 3, 1};
        TreeNode head1 = rebuildWithPreAndInOrderTraversal(pre, in);
        TreeUtils.printTreeBeautifully(head1);
    }
}
