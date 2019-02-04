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
        head.left = preAndIn(pre, preStart + 1, preStart + index - inStart,
                in, inStart, index - 1, indexMap);
        head.right = preAndIn(pre, preStart + index - inStart + 1, preEnd,
                in, index + 1, inEnd, indexMap);

        return head;
    }

    public static TreeNode rebuildWithInAndPostOrderTraversal(int[] in, int[] post) {
        if (in == null || post == null || in.length != post.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            indexMap.put(in[i], i);
        }

        return inAndPost(in, 0, in.length - 1, post, 0, post.length - 1, indexMap);
    }

    private static TreeNode inAndPost(int[] in, int inStart, int inEnd, int[] post, int postStart, int postEnd,
                                      HashMap<Integer, Integer> indexMap) {
        if (inStart > inEnd) {
            return null;
        }

        int val = post[postEnd];
        TreeNode head = new TreeNode(val);
        int index = indexMap.get(val);
        head.left = inAndPost(in, inStart, index - 1,
                post, postStart, postStart + index - inStart - 1, indexMap);
        head.right = inAndPost(in, index + 1, inEnd,
                post, postStart + index - inStart, postEnd - 1, indexMap);

        return head;
    }

    /**
     * Except for leaf nodes, all nodes in the binary tree must have both left and right nodes.
     *
     * @param pre
     * @param post
     * @return
     */
    public static TreeNode rebuildWithPreAndPostOrderTraversal(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length != post.length) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            indexMap.put(post[i], i);
        }

        return preAndPost(pre, 0, pre.length - 1, post, 0, post.length - 1, indexMap);
    }

    private static TreeNode preAndPost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd,
                                       HashMap<Integer, Integer> indexMap) {
        TreeNode head = new TreeNode(post[postEnd--]);
        if (preStart == preEnd) {
            return head;
        }

        int index = indexMap.get(pre[++preStart]);
        head.left = preAndPost(pre, preStart, preStart + index - postStart,
                post, postStart, index, indexMap);
        head.right = preAndPost(pre, preStart + index - postStart + 1, preEnd,
                post, index + 1, postEnd, indexMap);

        return head;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 8, 9, 3, 6, 7};
        int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
        int[] post = {4, 8, 9, 5, 2, 6, 7, 3, 1};
        TreeNode head1 = rebuildWithPreAndInOrderTraversal(pre, in);
        TreeUtils.printTreeBeautifully(head1);
        TreeNode head2 = rebuildWithInAndPostOrderTraversal(in, post);
        TreeUtils.printTreeBeautifully(head2);
        TreeNode head3 = rebuildWithPreAndPostOrderTraversal(pre, post);
        TreeUtils.printTreeBeautifully(head3);
    }
}
